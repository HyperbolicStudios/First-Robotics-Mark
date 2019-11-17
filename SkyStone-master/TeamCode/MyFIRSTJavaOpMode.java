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
    private DcMotor motorLeft;
    private DcMotor motorRight;
    private DcMotor arm1;
    private DcMotor arm2;
    private Servo Grab;



    @Override
    public void runOpMode() {
        motorLeft = hardwareMap.get(DcMotor.class, "left");
        motorRight = hardwareMap.get(DcMotor.class, "right");
        arm1 = hardwareMap.get(DcMotor.class, "Arm1");
        arm2 = hardwareMap.get(DcMotor.class, "Arm2");//digitalTouch = hardwareMap.get(DigitalChannel.class, "digitalTouch");
        Grab = hardwareMap.get(Servo.class, "Grab");
        telemetry.addData("Status", "Initialized");
        telemetry.update();         // Wait for the game to start (driver presses PLAY)
        waitForStart();
        // run until the end of the match (driver presses STOP)
        double powerLeft = 0;
        double powerRight = 0;
        double armPower = 0;
        while (opModeIsActive()) {
            x = this.gamepad1.left_stick_x;
            y = this.gamepad1.left_stick_y;
            h = Math.sqrt(x^2 + y^2);

            if (y > 0) {
                if (x > 0) {
                    powerLeft = h;
                    powerRight = h;
                } else if (x == 0){
                    powerLeft = h;
                    powerRight = -h;
                } else if (x < 0) {
                    powerLeft = -h;
                    powerRight = -h;
                }
            } else if (y == 0) {
                if (x > 0) {
                    powerLeft = h;
                    powerRight = h;
                } else if (x == 0) {
                    powerLeft = 0;
                    powerRight = 0;
                } else if (x < 0) {
                    powerLeft = -h;
                    powerRight = -h;s
                }
            } else if (y < 0) {
                if (x > 0) {
                    powerLeft = -h;
                    powerRight = -h;
                } else if (x == 0) {
                    powerLeft = -h;
                    powerRight = h;
                } else if (x < 0) {
                    powerLeft = h;
                    powerRight = h;
                }
            }

            if(gamepad1.a){
                Grab.setPosition(1);
            } else if (gamepad1.b) {
                Grab.setPosition(0);
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
            telemetry.addData("Servo Position", servoTest.getPosition());
            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
}
