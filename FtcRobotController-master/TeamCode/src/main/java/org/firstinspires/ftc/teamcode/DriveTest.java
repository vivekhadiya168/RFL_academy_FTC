
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Drive_Test")
public class DriveTest extends LinearOpMode {

    double drive_power = 0.8;

    private DriveRobot robot;

    @Override
    public void runOpMode() {

        // Initialize robot class
        robot = new DriveRobot(hardwareMap);

        telemetry.addLine("Robot Ready");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            // Read joystick values
            double drive = -gamepad1.left_stick_y * drive_power;
            double strafe = gamepad1.left_stick_x * drive_power;
            double turn = gamepad1.right_stick_x * drive_power;

            // Call drive method from DriveRobot class
            robot.driveRobot(drive, strafe, turn);

            // Telemetry
            telemetry.addData("Drive", drive);
            telemetry.addData("Strafe", strafe);
            telemetry.addData("Turn", turn);

            telemetry.addData("Drive Power", drive_power);
            telemetry.update();
        }

        // Stop robot
        robot.stopRobot();
    }
}
