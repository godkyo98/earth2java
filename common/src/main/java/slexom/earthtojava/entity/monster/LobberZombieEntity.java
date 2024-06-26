package slexom.earthtojava.entity.monster;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import slexom.earthtojava.entity.base.E2JBaseZombieEntity;
import slexom.earthtojava.entity.passive.FurnaceGolemEntity;
import slexom.earthtojava.entity.passive.MelonGolemEntity;
import slexom.earthtojava.entity.projectile.RottenFleshProjectileEntity;
import slexom.earthtojava.init.SoundEventsInit;

public class LobberZombieEntity extends E2JBaseZombieEntity implements RangedAttackMob {

    public LobberZombieEntity(EntityType<? extends Zombie> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected void addBehaviourGoals() {
        goalSelector.addGoal(2, new RangedAttackGoal(this, 1.25D, 40, 10.0F));
        goalSelector.addGoal(6, new MoveThroughVillageGoal(this, 1.0D, true, 4, this::canBreakDoors));
        goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(ZombifiedPiglin.class));
        targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, FurnaceGolemEntity.class, true));
        targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, MelonGolemEntity.class, true));
        targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
    }


    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {
        RottenFleshProjectileEntity rottenFleshProjectileEntity = new RottenFleshProjectileEntity(level(), this);
        double d0 = target.getEyeY() - 1.1D;
        double d1 = target.getX() - getX();
        double d2 = d0 - rottenFleshProjectileEntity.getY();
        double d3 = target.getZ() - getZ();
        double f = Math.sqrt(d1 * d1 + d3 * d3) * 0.2D;
        rottenFleshProjectileEntity.shoot(d1, d2 + f, d3, 1.6F, 12.0F);
        swing(InteractionHand.MAIN_HAND);
        playSound(SoundEventsInit.LOBBER_ZOMBIE_ATTACK.get(), 1.0F, 1.0F / (getRandom().nextFloat() * 0.4F + 0.8F));
        level().addFreshEntity(rottenFleshProjectileEntity);
    }
}
