// 1.	Trains
// As a promising tinker, you are part of Dick Simnel’s locomotion scheduler team.
// Given a schedule containing the arrival and departure time of trains in a station, find the minimum number of platforms needed to avoid delay in any train’s arrival.
// 1.	Input
// •	The input will come from the console on two lines:
// o	The first line will be a sequence of numbers representing the train arrival times.
// o	The second line will be a sequence of numbers representing the train departure times.
// 2.	Output
// •	The output is a single integer representing the minimum number of platforms, so no trains are delayed.
// 3.	Constraints
// •	The input numbers will be valid floating-point numbers representing 24h clock system as an example
// [1.30, 14.20, 6.50, 4.20] etc.
// •	When two trains are scheduled to arrive and depart simultaneously, depart the train first.
// •	Arrivals and departures will always be sequences with equal length.
// input1
// 2.00 2.10 3.00 3.20 3.50 5.00
// 2.30 3.40 3.20 4.30 4.00 5.20
// output1 - 2
// input2
// 14.00 14.10 00.40 14.10 1.50
// 14.50 14.40 23.50 14.20 2.00
// output2 - 4

import java.util.Arrays;
import java.util.Scanner;

public class Trains {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // read the arrival times from the console and store them in an array
        double[] arrivalTimes = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToDouble(Double::parseDouble)
                .toArray();
        // read the departure times from the console and store them in an array
        double[] departureTimes = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToDouble(Double::parseDouble)
                .toArray();
        // sort the arrival and departure times
        Arrays.sort(arrivalTimes);
        Arrays.sort(departureTimes);
        // initialize variables
        int platformCount = 0;
        int maxPlatformCount = 0;
        int arrivalIndex = 0;
        int departureIndex = 0;
        // loop through the arrival and departure times
        while (arrivalIndex < arrivalTimes.length && departureIndex < departureTimes.length) {
            // if the current arrival time is less than the current departure time,
            // increment the platform count and move to the next arrival time
            if (arrivalTimes[arrivalIndex] < departureTimes[departureIndex]) {
                platformCount++;
                arrivalIndex++;
            } else {
                // if the current departure time is less than or equal to the current arrival
                // time, decrement the platform count and move to the next departure time
                platformCount--;
                departureIndex++;
            }
            // update the maximum platform count if the current platform count is greater
            // than the maximum platform count
            if (platformCount > maxPlatformCount) {
                maxPlatformCount = platformCount;
            }
        }
        // print the minimum number of platforms needed to avoid delay in any train’s
        // arrival
        System.out.println(maxPlatformCount);
    }
}

