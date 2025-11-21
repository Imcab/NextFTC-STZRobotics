package org.firstinspires.ftc.teamcode.Robot;

import static dev.nextftc.bindings.Bindings.button;
import static dev.nextftc.extensions.pedro.PedroComponent.follower;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Lib.STZLite.Geometry.Pose;
import org.firstinspires.ftc.teamcode.Robot.DriveCommands.DriveCommands;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Drive.ChassisConstants;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Drive.SuperChassis;

import dev.nextftc.bindings.BindingManager;
import dev.nextftc.bindings.Button;
import dev.nextftc.control.ControlSystem;
import dev.nextftc.core.components.Component;
import dev.nextftc.extensions.pedro.PedroComponent;
import dev.nextftc.ftc.NextFTCOpMode;
import kotlin.time.TimeSource;

@TeleOp(name = "Weilai Teleop")
public class TeleopMode extends NextFTCOpMode {

    private final SuperChassis chassis = SuperChassis.INSTANCE;

    private final Button a;
    private final Button b;
    private final Button options;

    Component pedro = new PedroComponent(ChassisConstants::buildPedroPathing);

    public TeleopMode(){
        addComponents(pedro);
        addComponents(chassis.asCOMPONENT());

        this.a = button(() -> gamepad1.a);
        this.b = button(() -> gamepad1.b);
        this.options = button(() -> gamepad1.options);

        ControlSystem a = ControlSystem.builder().build();

    }

    @Override
    public void onInit() {
        chassis.setPedroReady();
        chassis.setDefaultCommand(
                DriveCommands.runWithJoysticks(
                        chassis, ()-> gamepad1.left_stick_y,
                        ()-> gamepad1.right_stick_x,
                        ()-> gamepad1.left_stick_x,
                        false));

        options.whenTrue(DriveCommands.resetHeading(chassis));


    }

    @Override
    public void onStartButtonPressed() {

    }

    @Override
    public void onUpdate() {
        BindingManager.update();
    }
    @Override
    public void onStop() {
        BindingManager.reset();
    }


}
