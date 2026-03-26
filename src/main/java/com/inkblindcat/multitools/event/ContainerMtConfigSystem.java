package com.inkblindcat.multitools.event;

import com.hypixel.hytale.component.ArchetypeChunk;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.EntityEventSystem;
import com.hypixel.hytale.math.vector.Vector3i;
import com.hypixel.hytale.protocol.BlockPosition;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.event.events.ecs.UseBlockEvent;
import com.hypixel.hytale.server.core.inventory.container.ItemContainer;
import com.hypixel.hytale.server.core.inventory.container.ItemContainer.ItemContainerChangeEvent;
import com.hypixel.hytale.server.core.modules.entity.component.TransformComponent;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.chunk.WorldChunk;
import com.hypixel.hytale.server.core.universe.world.meta.BlockState;
import com.hypixel.hytale.server.core.universe.world.meta.state.ItemContainerState;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.core.util.TargetUtil;
import com.inkblindcat.multitools.util.MultitoolDurabilityUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ContainerMtConfigSystem extends EntityEventSystem<EntityStore, UseBlockEvent.Pre> {

    private final Set<ItemContainer> registeredContainers = ConcurrentHashMap.newKeySet();

    public ContainerMtConfigSystem() {
        super(UseBlockEvent.Pre.class);
    }

    @Override
    public void handle(
            int index,
            @Nonnull ArchetypeChunk<EntityStore> archetypeChunk,
            @Nonnull Store<EntityStore> store,
            @Nonnull CommandBuffer<EntityStore> commandBuffer,
            @Nonnull UseBlockEvent.Pre pre) {

        Ref<EntityStore> ref = archetypeChunk.getReferenceTo(index);
        Player player = store.getComponent(ref, Player.getComponentType());

        TransformComponent transform = archetypeChunk.getComponent(index, TransformComponent.getComponentType());

        if (transform == null)
            return;

        WorldChunk chunk = transform.getChunk();
        if (chunk == null)
            return;

        Vector3i target = TargetUtil.getTargetBlock(ref, 5, commandBuffer);
        if (target == null)
            return;

        BlockPosition pos = player.getWorld()
                .getBaseBlock(new BlockPosition(target.x, target.y, target.z));

        BlockState state = chunk.getState(pos.x, pos.y, pos.z);

        if (state instanceof ItemContainerState containerState) {

            ItemContainer container = containerState.getItemContainer();
            if (container != null) {
                if (registeredContainers.add(container)) {
                    // keep this registered to auto-migrate on changes to the same container
                    container.registerChangeEvent((ItemContainerChangeEvent evt) -> MultitoolDurabilityUtil
                            .migrateContainerDurability(container));
                }
                MultitoolDurabilityUtil.migrateContainerDurability(container);
            }
        }
    }

    @Nullable
    @Override
    public Query<EntityStore> getQuery() {
        return PlayerRef.getComponentType();
    }
}
