package com.maeldonnart;

import java.util.List;

import com.maeldonnart.twentytwo.common.Day;
import com.maeldonnart.twentytwo.dayfour.DayFour;
import com.maeldonnart.twentytwo.dayone.DayOne;
import com.maeldonnart.twentytwo.daythree.DayThree;
import com.maeldonnart.twentytwo.daytwo.DayTwo;

public class App {

    private static void getYearLabel() {
        System.out.println("""
        ##########
        #  2022  #
        ##########
        """); 
    }

    public static void main(String[] args) {
        getYearLabel();
        List.of(
            new DayOne(),
            new DayTwo(),
            new DayThree(),
            new DayFour()
        ).forEach(Day::resolve);
    }
}
