package slexom.earthtojava.entity.base;


import net.minecraft.core.registries.Registries;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Shearable;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootTable;


public class E2JBaseMonoColorSheepEntity extends E2JBaseSheepEntity implements Shearable {

    private static final EntityDataAccessor<Byte> isSheared = SynchedEntityData.defineId(E2JBaseMonoColorSheepEntity.class, EntityDataSerializers.BYTE);
    private final ItemStack wool;

    public E2JBaseMonoColorSheepEntity(EntityType<? extends E2JBaseSheepEntity> type, Level world, ItemStack wool) {
        super(type, world);
        this.wool = wool;
    }

    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(isSheared, (byte) 0);
    }

    @Override
    public ResourceKey<LootTable> getDefaultLootTable() {
        if (isSheared()) {
            return ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.withDefaultNamespace("entities/sheep"));
        }
        return getType().getDefaultLootTable();
    }

    public boolean isSheared() {
        return (entityData.get(isSheared) & 16) != 0;
    }

    public void setSheared(boolean sheared) {
        byte b0 = entityData.get(isSheared);
        if (sheared) {
            entityData.set(isSheared, (byte) (b0 | 16));
        } else {
            entityData.set(isSheared, (byte) (b0 & -17));
        }
    }

    public boolean readyForShearing() {
        return isAlive() && !isSheared() && !isBaby();
    }

    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (itemStack.is(Items.SHEARS)) {
            if (!level().isClientSide && readyForShearing()) {
                shear(SoundSource.PLAYERS);
                itemStack.hurtAndBreak(1, player, getSlotForHand(hand));
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.CONSUME;
        }
        return super.mobInteract(player, hand);
    }

    public void shear(SoundSource soundSource) {
        level().playSound(null, this, SoundEvents.SHEEP_SHEAR, soundSource, 1.0F, 1.0F);
        setSheared(true);
        int i = 1 + random.nextInt(3);
        for (int j = 0; j < i; ++j) {
            ItemEntity itemEntity = spawnAtLocation(wool.getItem(), 1);
            if (itemEntity != null) {
                itemEntity.setDeltaMovement(itemEntity.getDeltaMovement().add((random.nextFloat() - random.nextFloat()) * 0.1F, random.nextFloat() * 0.05F, (random.nextFloat() - random.nextFloat()) * 0.1F));
            }
        }
    }

}
