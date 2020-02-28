package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;  //IMPORT DIFFERENT STUFF - NOT IMPORTANT
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
    private DcMotor motorLeft; //Not relevant
    private DcMotor motorRight;
    private DcMotor armA1;
    private DcMotor motorStrafe;
    private Servo leftFound;
    private Servo rightFound;
    private Servo grab;
    private Servo grab2;
    private Servo grabPivot;
    private DistanceSensor front;

    @Override
    public void runOpMode() {
        motorLeft = hardwareMap.get(DcMotor.class, "left");     //Create devices - don't worry about this
        motorRight = hardwareMap.get(DcMotor.class, "right");
        armA1 = hardwareMap.get(DcMotor.class, "ArmA1");
        motorStrafe = hardwareMap.get(DcMotor.class, "strafe");
        grab = hardwareMap.get(Servo.class, "grab");
        grab2 = hardwareMap.get(Servo.class, "grab2");
        grabPivot = hardwareMap.get(Servo.class, "grabPivot");
        leftFound = hardwareMap.get(Servo.class, "found1");
        rightFound = hardwareMap.get(Servo.class, "found2");
        telemetry.addData("Status", "Initialized");
        telemetry.update();         // Wait for the game to start (driver presses PLAY)
        waitForStart();
        // run until the end of the match (driver presses STOP)
        double powerLeft = 0; //Variables used throughout the program
        double powerRight = 0;
        double armPower = 0;
        double strafePower = 0;
        double y;
        boolean prec = false;
        while (opModeIsActive()) {
            leftFound.setPosition(90);
            rightFound.setPosition(90);

            y = this.gamepad1.left_stick_y; //Get y value of joystick

            //check for precision mode
            if (gamepad1.right_trigger > 0) {
                prec = true;
            } else {
                prec = false;
            }

            armPower = y / 2;




//Check for strafe power - this will not override other motor powers.

            if (gamepad1.dpad_left) {
                strafePower = 1;
            }
            else if (gamepad1.dpad_right) {
                strafePower = - 1;
            }
            else {
                strafePower = 0;
            }//if else

            if (gamepad1.dpad_up) {
                powerLeft = 1;
                powerRight = -1;
            }
            else if (gamepad1.dpad_down) {
                powerLeft = -1;
                powerRight = 1;
            }
            else {
                powerLeft = 0;
                powerRight = 0;
            }


//Check if the bumper is pressed; this causes a pivot. A pivot will OVERRIDE previous math done.

            if (gamepad1.left_bumper) {//Pivot Counterclockwise
                powerLeft = -1; //Maximum right power
                powerRight = -1; //Maximum left power (other direction)
            }
            else if (gamepad1.right_bumper) { //Clockwise direction
                powerRight = 1; //Likewise
                powerLeft = 1;
            }//if else

//Reduces power if precision mode is enabled

        if (prec) {
            powerLeft /= 2;
            powerRight /= 2;
            armPower /= 2;
            strafePower /= 2;
        }

          //At this point, the motors have been set
           // timeNow = java.time.LocalTime.now());
         if(gamepad1.a) {
                grab.setPosition(180);
                grab2.setPosition(0);

            }//open
          else if (gamepad1.b) {
                grab.setPosition(0);
                grab2.setPosition(180);
            }//close

          if (gamepad1.x) {
              grabPivot.setPosition(0);
          } else if (gamepad1.y) {
              grabPivot.setPosition(180);
          }




//TRANSMIT POWER INFO TO HUB
            motorLeft.setPower(powerLeft); //Drive
            motorRight.setPower(powerRight);
            motorStrafe.setPower(strafePower); //Strafe
            armA1.setPower(armPower); //Stage 1


            telemetry.addData("Left Drive Motor Power", motorLeft.getPower()); //Print info to Driver Phone
            telemetry.addData("Right Drive Motor Power", motorRight.getPower());
            telemetry.addData("Strafe Power", motorStrafe.getPower());
            telemetry.addData("Arm Power", armA1.getPower());

            //telemetry.addData("R Servo Position", RightFound.getPosition());
            //telemetry.addData("L Servo Position: ", LeftFound.getPosition());
            telemetry.addData("Status", "Running");
            telemetry.update();  //update
        }
    }}