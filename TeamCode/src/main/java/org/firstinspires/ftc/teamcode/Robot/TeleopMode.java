package org.firstinspires.ftc.teamcode.Robot;

import static dev.nextftc.bindings.Bindings.button;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot.DriveCommands.DriveCommands;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Drive.ChassisConstants;
import org.firstinspires.ftc.teamcode.Robot.Subsystems.Drive.SuperChassis;

import dev.nextftc.bindings.BindingManager;
import dev.nextftc.bindings.Button;
import dev.nextftc.control.ControlSystem;
import dev.nextftc.extensions.pedro.PedroComponent;
import dev.nextftc.ftc.NextFTCOpMode;
import kotlin.time.TimeSource;

@TeleOp(name = "NextFTC Teleop Program 2 Java")
public class TeleopMode extends NextFTCOpMode {

    private final SuperChassis chassis = SuperChassis.INSTANCE;

    private final Button a;
    private final Button b;
    private final Button options;

    public TeleopMode(){
        addComponents(new PedroComponent(ChassisConstants::buildPedroPathing),chassis.asCOMPONENT());

        chassis.setDefaultCommand(DriveCommands.runWithJoysticks(chassis, ()-> gamepad1.left_stick_y, ()-> gamepad1.right_stick_x, ()-> gamepad1.left_stick_x, false));

        this.a = button(() -> gamepad1.a);
        this.b = button(() -> gamepad1.b);
        this.options = button(() -> gamepad1.options);

        ControlSystem a = ControlSystem.builder().build();

    }

    @Override
    public void onInit() {

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
