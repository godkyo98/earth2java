package slexom.earthtojava.entity.monster;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import slexom.earthtojava.entity.BlinkManager;
import slexom.earthtojava.init.SoundEventsInit;

public class VilerWitchEntity extends Witch {


    public final BlinkManager blinkManager;

    private int castingTicks = 0;

    public VilerWitchEntity(EntityType<? extends Witch> entityType, Level world) {
        super(entityType, world);
        blinkManager = new BlinkManager();
    }


    @Override
    public void aiStep() {
        super.aiStep();
        blinkManager.tickBlink();
        if (castingTicks > 0) {
            --castingTicks;
        }
    }

    @Environment(EnvType.CLIENT)
    public int getCastingTicks() {
        return castingTicks;
    }

    @Override
    public void performRangedAttack(LivingEntity target, float pullProgress) {
        if (isDrinkingPotion()) return;

        castingTicks = random.nextInt(20) + 40;
        Vec3 vec3d = target.getDeltaMovement();
        double dX = target.getX() + vec3d.x - getX();
        double dY = target.getEyeY() - 1.1d - getY();
        double dZ = target.getZ() + vec3d.z - getZ();
        double distance = Math.sqrt(dX * dX + dZ * dZ);
        Holder<Potion> potion = Potions.HARMING;
        if (target instanceof Raider) {
            if (target.getHealth() <= 4.0F) {
                potion = Potions.HEALING;
            } else {
                potion = Potions.REGENERATION;
            }
            setTarget(null);
        } else if (distance >= 8.0D && !target.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
            potion = Potions.SLOWNESS;
        } else if (target.getHealth() >= 8.0F && !target.hasEffect(MobEffects.POISON)) {
            potion = Potions.POISON;
        } else if (distance <= 3.0D && !target.hasEffect(MobEffects.WEAKNESS) && random.nextFloat() < 0.25F) {
            potion = Potions.WEAKNESS;
        }

        ThrownPotion thrownPotion = new ThrownPotion(this.level(), this);
        if (potion == Potions.HARMING || potion == Potions.POISON) {
            thrownPotion.setItem(PotionContents.createItemStack(Items.LINGERING_POTION, potion));
        } else {
            thrownPotion.setItem(PotionContents.createItemStack(Items.SPLASH_POTION, potion));
        }

        thrownPotion.setXRot(thrownPotion.getXRot() - -20.0F);
        thrownPotion.shoot(dX, dY + (distance * 0.2D), dZ, 0.75F, 8.0F);

        if (!isSilent()) {
            level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEventsInit.VILER_WITCH_CASTING.get(), this.getSoundSource(), 1.0F, 0.8F + this.random.nextFloat() * 0.4F);
        }

        level().addFreshEntity(thrownPotion);
    }

}
