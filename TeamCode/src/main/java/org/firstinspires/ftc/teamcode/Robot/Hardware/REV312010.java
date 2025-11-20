package org.firstinspires.ftc.teamcode.Robot.Hardware;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

import dev.nextftc.ftc.ActiveOpMode;

public class REV312010 {

    private final DigitalChannel redLED;
    private final DigitalChannel greenLED;

    public REV312010(){
        HardwareMap map = ActiveOpMode.hardwareMap();
        redLED = map.get(DigitalChannel.class, "red");
        greenLED = map.get(DigitalChannel.class, "green");

        redLED.setMode(DigitalChannel.Mode.OUTPUT);
        greenLED.setMode(DigitalChannel.Mode.OUTPUT);
    }

    public void setGreen(){
        greenLED.setState(true);
        redLED.setState(false);
    }

    public void setRed(){
        greenLED.setState(false);
        redLED.setState(true);
    }

    public void setAmber(){
        greenLED.setState(true);
        redLED.setState(true);
    }

    public void setOff(){
        greenLED.setState(false);
        redLED.setState(false);
    }
}

