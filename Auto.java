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

            //Drive forward

            //Wait for Button to be pressed

            //Drop Servos

            //Drive backward

            //Wait for rear button to be pressed

            //Strafe Sideways





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
