package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.Gamepad;
import org.firstinspires.ftc.robotcontroller.external.samples.SensorREVColorDistance;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp //FIX

public class MyFIRSTJavaOpMode extends LinearOpMode {
    private DcMotor motorLeft;
    private DcMotor motorRight;
    private DcMotor armA1;
    private DcMotor motorStrafe;
    private Servo chassis1;
    private Servo chassis2;
    private Servo Grab;
    private DistanceSensor sensorCRFront;



    @Override
    public void runOpMode() {
        motorLeft = hardwareMap.get(DcMotor.class, "left");
        motorRight = hardwareMap.get(DcMotor.class, "right");
        armA1 = hardwareMap.get(DcMotor.class, "ArmA1");
        motorStrafe = hardwareMap.get(DcMotor.class, "strafe");
        Grab = hardwareMap.get(Servo.class, "Grab");
        chassis1 = hardwareMap.get(Servo.class, "chassis1");
        chassis2 = hardwareMap.get(Servo.class, "chassis2");
        sensorCRFront = hardwareMap.get(DistanceSensor.class, "sensorColorRangeFront");
        telemetry.addData("Status", "Initialized");
        telemetry.update();         // Wait for the game to start (driver presses PLAY)
        waitForStart();
        // run until the end of the match (driver presses STOP)
        double powerLeft = 0;
        double powerRight = 0;
        double armPower = 0;
        double strafePower = 0;
        double x;
        double y;
        double h;
        while (opModeIsActive()) {
            x = this.gamepad1.left_stick_x; //Get x value
            y = this.gamepad1.left_stick_y; //Get y value
            h = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)); //Get distance from center ("h")

            if(y < 0) { //allow reverse movement
                h = h*-1;
            }

            powerLeft = -h; //Set initial power value
            powerRight = h;

            if (x > 0) { //If the joystick is on the right (range from 0 to 1)
                powerRight *= x; //Multiply the right motor by x
            } else if (x < 0) {//If the joystick is on the left (range from -1 to 0)
                powerLeft *= -x; //Multiply the left motor by x
            }


//Check if the bumper is pressed; this causes a pivot. A pivot will OVERRIDE previous math done.

            if (gamepad1.left_bumper) {//Pivot Counterclockwise
                powerRight = 1; //Maximum right power
                powerLeft = 1; //Maximum left power (other direction)
            }
            else if (gamepad1.right_bumper) { //Clockwise direction
                powerRight = -1; //Likewise
                powerLeft = -1;
            }


//Check for strafe power - this will not overide other motor powers.

            if (gamepad1.dpad_left) {
                strafePower = 1;
            }
            else if (gamepad1.dpad_right) {
                strafePower = - 1;
            }
            else {
                strafePower = 0;
            }
//At this point, the drive motors have been set.

            if(gamepad1.a){ //First Stage Arm
                armPower = 0.4;
            } else if (gamepad1.b) {
                armPower = -0.4;
            } else {
                armPower = 0;
            }

            if(gamepad1.x) {
                chassis1.setPosition(90);
                chassis1.setPosition(90);
            }
            else if(gamepad1.y) {
                chassis1.setPosition(0);
                chassis2.setPosition(0);
            }



            if(this.gamepad1.right_stick_y < 0){ //Grabber
                Grab.setPosition(1);
            } else if (this.gamepad1.right_stick_y > 0) {
                Grab.setPosition(0);
            } else {
                Grab.setPosition(.6);
            }





//TRANSMIT POWER INFO TO HUB
            motorLeft.setPower(powerLeft); //Drive
            motorRight.setPower(powerRight);
            motorStrafe.setPower(strafePower);
            armA1.setPower(armPower); //Stage 1


            telemetry.addData("Left Drive Motor Power", motorLeft.getPower());
            telemetry.addData("Right Drive Motor Power", motorRight.getPower());
            telemetry.addData("Strafe Power", motorStrafe.getPower());
            telemetry.addData("Arm Power", armA1.getPower());

            telemetry.addData("Servo Position", Grab.getPosition());
            telemetry.addData("Distance (cm)", sensorCRFront.getDistance(DistanceUnit.CM));
            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
}
