package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp

public class MyFIRSTJavaOpMode extends LinearOpMode {
    private Gyroscope imu;
    private DcMotor motorLeft;
    private DcMotor motorRight;
    private DcMotor arm1;
    private DcMotor arm2;
    private DigitalChannel digitalTouch;
    private DistanceSensor sensorColorRange;
    private Servo servoTest;



    @Override
    public void runOpMode() {
        //imu = hardwareMap.get(Gyroscope.class, "imu");
        motorLeft = hardwareMap.get(DcMotor.class, "left");
        motorRight = hardwareMap.get(DcMotor.class, "right");
        arm1 = hardwareMap.get(DcMotor.class, "Arm1");
        arm2 = hardwareMap.get(DcMotor.class, "Arm2");
        //digitalTouch = hardwareMap.get(DigitalChannel.class, "digitalTouch");
        //sensorColorRange = hardwareMap.get(DistanceSensor.class, "sensorColorRange");
        //servoTest = hardwareMap.get(Servo.class, "servoTest");
        telemetry.addData("Status", "Initialized");
        telemetry.update();         // Wait for the game to start (driver presses PLAY)
        waitForStart();
        // run until the end of the match (driver presses STOP)
        double powerLeft = 0;
        double powerRight = 0;
        double armPower = 0;
        while (opModeIsActive()) {
            powerLeft = -this.gamepad1.left_stick_y;
            powerRight = this.gamepad1.left_stick_y;

            if (gamepad1.right_bumper){
                powerLeft = -1;
                powerRight = -1;
            } else if (gamepad1.left_bumper){
                powerLeft = 1;
                powerRight = 1;
            }

            armPower = this.gamepad1.right_stick_x;

            motorLeft.setPower(powerLeft);
            motorRight.setPower((powerRight));
            arm1.setPower(armPower);
            arm2.setPower(-armPower);
            telemetry.addData("Target Power", powerLeft);
            telemetry.addData("Left Motor Power", motorLeft.getPower());
            telemetry.addData("Right Motor Power", motorRight.getPower());
            telemetry.addData("Arm 1 Power", arm1.getPower());
            telemetry.addData("Arm 2 Power", arm2.getPower());
            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
}
