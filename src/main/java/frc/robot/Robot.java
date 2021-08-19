// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import java.lang.Math;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private  WPI_TalonSRX leftMotor;
  private  WPI_TalonSRX rightMotor;
  private  WPI_TalonSRX leftMotor2;
  private  WPI_TalonSRX rightMotor2;

  private  WPI_TalonSRX elevatorMotorUp;
  private  WPI_TalonSRX elevatorMotorDown;
  private  WPI_TalonSRX feederMotor;
  private  TalonFX shooter;

  private  DifferentialDrive robotDrive;
  // private Servo servo;
  // private Solenoid solenoid;
  private WPI_TalonSRX quantumIntake;

  private Joystick leftStick;
  private Joystick rightStick;
  private XboxController controller;
  //private Hand hand;
  //private Hand kRight;

  private Boolean leftTrigger;
  private Boolean rightTrigger;
  private Boolean leftMiddleBottom;
  private Boolean rightMiddleBottom;

  public Robot() {
    super(0.03);
    leftMotor = new WPI_TalonSRX(RobotConstants.leftMotorID);
    rightMotor = new WPI_TalonSRX(RobotConstants.rightMotorID);
    leftMotor2 = new WPI_TalonSRX(RobotConstants.leftMotor2ID);
    rightMotor2 = new WPI_TalonSRX(RobotConstants.rightMotor2ID);

    elevatorMotorUp = new WPI_TalonSRX(RobotConstants.elevatorMotorUpID);
    //elevatorMotorDown = new WPI_TalonSRX(RobotConstants.elevatorMotorDownID);
    feederMotor = new WPI_TalonSRX(RobotConstants.feederMotorID);

    robotDrive = new DifferentialDrive(leftMotor, rightMotor);
    //servo = new Servo(0);
    //solenoid = new Solenoid(0);
    quantumIntake = new WPI_TalonSRX(RobotConstants.quantumIntakeID);

    shooter = new TalonFX(RobotConstants.shooterID);

    leftStick = new Joystick(0);
    rightStick = new Joystick(1);
    controller = new XboxController(0);
    leftTrigger = leftStick.getRawButton(1);
    rightTrigger = rightStick.getRawButton(1);
    leftTrigger = leftStick.getRawButton(1);
    rightTrigger = rightStick.getRawButton(1);
    leftMiddleBottom = leftStick.getRawButton(2);
    rightMiddleBottom = rightStick.getRawButton(2);
    //hand = k;
    //kRight = new Hand();
  }

  @Override
  public void robotInit() {
    leftMotor.setNeutralMode(NeutralMode.Brake);
    rightMotor.setNeutralMode(NeutralMode.Brake);
    leftMotor2.setNeutralMode(NeutralMode.Brake);
    rightMotor2.setNeutralMode(NeutralMode.Brake);

    leftMotor2.follow(leftMotor);
    rightMotor2.follow(rightMotor);
  }

  @Override
  public void teleopInit() {
    // servo.setAngle(0);
    // solenoid.set(false);
  }

  @Override
  public void teleopPeriodic() {
    
    /*System.out.println("Left Stick X: "+leftStick.getX());
    System.out.println("Left Stick Y: "+leftStick.getY());
    System.out.println("Right Stick X: "+rightStick.getX());
    System.out.println("Right Stick Y: "+rightStick.getY());*/
    /*System.out.println("Left Trigger: "+leftTrigger);
    System.out.println("Right Trigger" +rightTrigger);*/
    /*
    for (int i = 1; i < 8; i++) {
      if (leftStick.getRawButton(i))
        System.out.println("Left Button " + i + " " + leftStick.getRawButton(i));
    }
    for (int i = 1; i < 8; i++) {
      if (rightStick.getRawButton(i))
        System.out.println("Right Button " + i + " " + rightStick.getRawButton(i));
    }*/
    
    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.
    //robotDrive.arcadeDrive(leftStick.getY(), -leftStick.getX());

    // original tank drive 
    robotDrive.tankDrive(-(Math.abs(leftStick.getY())*leftStick.getY()), -(Math.abs(rightStick.getY())*rightStick.getY()));

    // smarter tank drive combination of arcade and tank
    // use left stick to control steering
    // hold the stick forward to go forward and backward to go backward
    // hold the stick left to turn left sharply and same with right
    // Letting go of the stick reverts control back to regular tank drive

    /*
    if (leftTrigger)
      robotDrive.tankDrive(controller.getTriggerAxis(Hand.kLeft), controller.getTriggerAxis(Hand.kRight));
    else
      robotDrive.tankDrive((leftstick.getX()+leftstick.getY())*controller.getTriggerAxis(Hand.kLeft), (-leftstick.getX()+leftstick.getY())*controller.getTriggerAxis(Hand.kRight));
    */
    if(rightStick.getRawButton(1))
      quantumIntake.set(-.2);
    
    else 
      quantumIntake.set(0);
    

    if(leftStick.getRawButton(1)) 
      feederMotor.set(-.5);
    
    else 
      feederMotor.set(0);
    
    // if (leftStick.getY()>0) {
    //   elevatorMotorUp.set(1);
    //   elevatorMotorDown.set(0);
    // }
    // else if (leftStick.getY()<0) {
    //   //elevatorMotorDown.set(1);
    //   elevatorMotorUp.set(0);
    // }
    // else {
    //   //elevatorMotorDown.set(0);
    //   elevatorMotorUp.set(0);
    // }

    if(rightStick.getRawButton(2))
      shooter.set(ControlMode.PercentOutput, (rightStick.getZ()-1)/2);
    else
      shooter.set(ControlMode.PercentOutput, 0);
  }
}