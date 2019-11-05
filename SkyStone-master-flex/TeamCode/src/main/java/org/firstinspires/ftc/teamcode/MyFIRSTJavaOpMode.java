package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorREVColorDistance;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp

public class MyFIRSTJavaOpMode extends LinearOpMode {
    private DcMotor motorLeft;
    private DcMotor motorRight;
    private DcMotor arm1;
    private DcMotor arm2;
    //private  DcMotor arm21;
    private Servo Grab;
    private DistanceSensor sensorCRFront;



    @Override
    public void runOpMode() {
        motorLeft = hardwareMap.get(DcMotor.class, "left");
        motorRight = hardwareMap.get(DcMotor.class, "right");
        arm1 = hardwareMap.get(DcMotor.class, "Arm1");
        arm2 = hardwareMap.get(DcMotor.class, "Arm2");//digitalTouch = hardwareMap.get(DigitalChannel.class, "digitalTouch");
        //arm21 = hardwareMap.get(DcMotor.class, "Arm21");//digitalTouch = hardwareMap.get(DigitalChannel.class, "digitalTouch");
        Grab = hardwareMap.get(Servo.class, "Grab");
        sensorCRFront = hardwareMap.get(DistanceSensor.class, "sensorColorRangeFront");
        telemetry.addData("Status", "Initialized");
        telemetry.update();         // Wait for the game to start (driver presses PLAY)
        waitForStart();
        // run until the end of the match (driver presses STOP)
        double powerLeft = 0;
        double powerRight = 0;
        double armPower = 0;
        double armPower2 = 0;
        double x;
        double y;
        double h;
        while (opModeIsActive()) {
            x = this.gamepad1.left_stick_x;
            y = this.gamepad1.left_stick_y;
            h = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

            powerLeft = -h;
            powerRight = h;

            if (gamepad1.left_bumper) {
                powerLeft *= -1;
            } else if (gamepad1.right_bumper) {
                powerRight *= -1;
            }

            if (x > 0) {
                powerRight *= x;
            } else if (x < 0) {
                powerLeft *= x;
            }


            if(gamepad1.a){
                Grab.setPosition(1);
            } else if (gamepad1.b) {
                Grab.setPosition(0);
            } else {
                Grab.setPosition(.5);
            }



            armPower = this.gamepad1.right_trigger;
            armPower2 = this.gamepad1.left_trigger;

            motorLeft.setPower(powerLeft);
            motorRight.setPower((powerRight));
            arm1.setPower(armPower);
            arm2.setPower(-armPower);
            arm21.setPower(armPower2);
            telemetry.addData("Left Motor Power", motorLeft.getPower());
            telemetry.addData("Right Motor Power", motorRight.getPower());
            telemetry.addData("Stage 1 Power", arm1.getPower());
            //telemetry.addData("Stage 2 Power", arm21.getPower());
            telemetry.addData("Servo Position", Grab.getPosition());
            telemetry.addData("Distance (cm)", sensorCRFront.getDistance(DistanceUnit.CM));
            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
}
