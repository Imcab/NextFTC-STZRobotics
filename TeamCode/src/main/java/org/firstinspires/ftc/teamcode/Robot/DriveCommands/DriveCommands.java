package org.firstinspires.ftc.teamcode.Robot.DriveCommands;

import static dev.nextftc.extensions.pedro.PedroComponent.follower;

import com.pedropathing.paths.Path;

import org.firstinspires.ftc.teamcode.Lib.STZLite.Geometry.Rotation;
import org.firstinspires.ftc.teamcode.Lib.STZLite.Math.Utils.Units;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Drive.SuperChassis;

import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.utility.InstantCommand;
import dev.nextftc.core.commands.utility.LambdaCommand;
import dev.nextftc.core.units.Angle;
import dev.nextftc.extensions.pedro.FollowPath;
import dev.nextftc.extensions.pedro.TurnBy;
import dev.nextftc.extensions.pedro.TurnTo;

public class DriveCommands {

    // DriveCommands.java

    public static Command runWithJoysticks(SuperChassis chasis, Supplier<Float> forward, Supplier<Float> strafe, Supplier<Float> turn, boolean robotCentric){
        return new LambdaCommand().
                named("RunWithJoysticks").
                requires(chasis).
                // setStart(follower()::startTeleOpDrive).  <-- ¡ELIMINAR ESTA LÍNEA!
                        setUpdate(
                        ()-> {
                            // 1. Obtener los valores del joystick
                            float fw = forward.get();
                            float st = strafe.get();
                            float tr = turn.get();

                            // 2. Proteger la lógica de Pedro, verificando el flag del chasis
                            if (chasis.isPedroReady) {
                                // En el primer ciclo donde isPedroReady es true,
                                // se llamará a startTeleOpDrive automáticamente si es necesario.
                                follower().setTeleOpDrive(fw, st, tr, robotCentric);
                            }
                        }
                ).
                // ... resto del comando ...
                        setIsDone(()-> false); // Debería ser false para un default command
    }

    public static Command platilla(){
        return  new LambdaCommand().
                named("").
                requires(null).
                setStart(()-> {

                }).
                setUpdate(()-> {

                }).
                setStop(interrupted-> {

                }).
                setIsDone(()-> true).setInterruptible(true).
                setInterruptible(false);
    }

    public static Command resetHeading(SuperChassis chassis){
        return new InstantCommand("Reset Heading", chassis::resetHeading);
    }

    public static Command toPath(SuperChassis chassis, Path path, boolean hold, double maxPower){
        Command follower = new FollowPath(path, hold, maxPower);
        follower.named("Follow Command").requires(chassis);

        return  follower;

    }

    public static Command toPath(SuperChassis chassis, Path path, boolean hold){
        return toPath(chassis, path, hold, 1);
    }

    public static Command toPath(SuperChassis chassis, Path path){
        return toPath(chassis, path, false);
    }

    public static Command Turn(SuperChassis chassis, double angleDeg){
        double radTo = Units.degreesToRadians(angleDeg);
        Command turn = new TurnTo(Angle.fromRad(radTo));
        turn.named("Turn Command").requires(chassis);
        return turn;
    }

    public static Command Turn(SuperChassis chassis, Angle angleRads) {
        Command turn = new TurnTo(angleRads);
        turn.named("Turn Command").requires(chassis);
        return turn;
    }

    public static Command Turn(SuperChassis chassis, Rotation rotRads){
        return Turn(chassis, rotRads.toAngle());
    }

    public static Command TurnBy(SuperChassis chassis, double angleDeg){
        double radTo = Units.degreesToRadians(angleDeg);
        Command turn = new TurnBy(Angle.fromRad(radTo));
        turn.named("TurnBy Command").requires(chassis);
        return turn;
    }

    public static Command TurnBy(SuperChassis chassis, Angle angleRads) {
        Command turn = new TurnBy(angleRads);
        turn.named("TurnBy Command").requires(chassis);
        return turn;
    }

    public static Command TurnBy(SuperChassis chassis, Rotation rotRads){
        return TurnBy(chassis, rotRads.toAngle());
    }

}
