package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Mecanum 10s Drive", group = "Test")
public class RobotHardWare extends LinearOpMode {

    private static final double DRIVE_POWER = 0.5; // Adjust between -1.0 and 1.0
    private static final double RUN_SECONDS = 10.0;

    @Override
    public void runOpMode() {
        // Match these names to your active Robot Controller hardware configuration
        DcMotor frontLeft = hardwareMap.get(DcMotor.class, "front_left");
        DcMotor frontRight = hardwareMap.get(DcMotor.class, "front_right");
        DcMotor backLeft = hardwareMap.get(DcMotor.class, "back_left");
        DcMotor backRight = hardwareMap.get(DcMotor.class, "back_right");

        // Reverse right-side motors if positive power spins them backward
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData("Status", "Initialized - ready for START");
        telemetry.update();

        waitForStart();

        ElapsedTime timer = new ElapsedTime();
        timer.reset();

        // Run all four motors until 10 seconds elapse or STOP is pressed
        while (opModeIsActive() && timer.seconds() < RUN_SECONDS) {
            frontLeft.setPower(DRIVE_POWER);
            frontRight.setPower(DRIVE_POWER);
            backLeft.setPower(DRIVE_POWER);
            backRight.setPower(DRIVE_POWER);

            telemetry.addData("Status", "Running");
            telemetry.addData("Elapsed (s)", "%.2f / %.1f", timer.seconds(), RUN_SECONDS);
            telemetry.update();
        }

        // Stop all motors permanently for this OpMode execution
        setAllPower(0.0);

        while (opModeIsActive()) {
            telemetry.addData("Status", "Stopped permanently until re-run");
            telemetry.addData("Elapsed (s)", "%.2f", timer.seconds());
            telemetry.update();
        }
    }

    private void setAllPower(double power) {
        DcMotor frontLeft = hardwareMap.get(DcMotor.class, "front_left");
        DcMotor frontRight = hardwareMap.get(DcMotor.class, "front_right");
        DcMotor backLeft = hardwareMap.get(DcMotor.class, "back_left");
        DcMotor backRight = hardwareMap.get(DcMotor.class, "back_right");
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
    }
}