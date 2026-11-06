package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

public class test_robot {

    private DcMotor fl;
    private DcMotor bl;
    private DcMotor fr;
    private DcMotor br;


    public test_robot(HardwareMap hardwareMap) {


        fl = hardwareMap.get(DcMotor.class, "FL");
        bl = hardwareMap.get(DcMotor.class, "BL");
        fr = hardwareMap.get(DcMotor.class, "FR");
        br = hardwareMap.get(DcMotor.class, "BR");

        fl.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.REVERSE);
        fr.setDirection(DcMotorSimple.Direction.FORWARD);
        br.setDirection(DcMotorSimple.Direction.REVERSE);

        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public void driveRobot(double drive, double strafe, double turn) {

        double FL = drive + strafe + turn;
        double FR = drive - strafe - turn;
        double BL = drive - strafe + turn;
        double BR = drive + strafe - turn;

        fl.setPower(FL);
        fr.setPower(FR);
        bl.setPower(BL);
        br.setPower(BR);

    }
}