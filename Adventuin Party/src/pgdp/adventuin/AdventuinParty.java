package pgdp.adventuin;

import pgdp.color.RgbColor;
import pgdp.color.RgbColor8Bit;

import java.util.*;

public final class AdventuinParty {

    public static Map<HatType, List<Adventuin>> groupByHatType(List<Adventuin> adventuins) {
        Map<HatType, List<Adventuin>> res = new HashMap<>();
        List<HatType> distHats = new ArrayList<>();

        adventuins.forEach(x -> distHats.add(x.getHatType()));
        distHats.stream().distinct();

        distHats.forEach(x -> {
            List<Adventuin> byHats = new ArrayList<>();
            adventuins.stream().forEach(a -> {
                if (x.equals(a.getHatType()) ) {
                    byHats.add(a);
                }
            });
        });
        return res;
    }


    public static String getLocalizedChristmasGreeting(Adventuin name) {
        return name.getLanguage().getLocalizedChristmasGreeting(name.getName());
    }

    public static void printLocalizedChristmasGreetings(List<Adventuin> adventuin) {
        adventuin.stream().sorted(Comparator.comparing(Adventuin::getHeight)).
                forEach(a -> System.out.println(getLocalizedChristmasGreeting(a)));
    }
    public static Map<HatType, List<Adventuin>> getAdventuinsWithLongestNamesByHatType (List<Adventuin> adventuins){
        List<Integer> names = new ArrayList<>();
        adventuins.stream().forEach(a -> {
            names.add(a.getName().length());
        });

        int maxLet = names.stream().max(Comparator.comparingInt(x -> x)).orElse(0);

        List<Adventuin> maxNames = adventuins.stream().filter(a -> a.getName().length() == maxLet).toList();

        return groupByHatType(maxNames);
    }

    public static Map<Integer, Double> getAverageColorBrightnessByHeight(List<Adventuin> adventuins) {
        Map<Integer, Double> res = new HashMap<>();
        List<Integer> heights = new ArrayList<>();

        adventuins.forEach(a -> {
            int roundHeight = rHeight(a);
            heights.add(roundHeight);
        });

        heights.stream().distinct().forEach(x -> {
            List<Double> bright = new ArrayList<>();

            adventuins.forEach(adventuin -> {
                if(rHeight(adventuin) == x){
                    bright.add(brightness(adventuin.getColor().toRgbColor8Bit()));
                }
            });

            double avg = bright.stream().mapToDouble(Double :: doubleValue).average().getAsDouble();

            res.put(x, avg);
        });
        return res;
    }

    private static int rHeight (Adventuin adventuin) {
        return (int) ((Math.round((double) adventuin.getHeight() / 10)) * 10);
    }

    private static double brightness(RgbColor8Bit color) {
        return (0.2126 * color.getRed() + 0.7152 * color.getGreen() + 0.0722 * color.getBlue());
    }

    public static Map<HatType, Double> getDiffOfAvgHeightDiffsToPredecessorByHatType(List<Adventuin> adventuins) {
        Map<HatType, Double> res = new HashMap<>();
        Map<HatType, List<Adventuin>> hat = groupByHatType(adventuins);

        hat.forEach((hatType, adv) -> {
            double posSum = 0.0;
            double negSum = 0.0;
            int posCount = 0;
            int negCount = 0;

            for (int i = 0; i < adv.size(); i++) {
                int diff = adv.get(i).getHeight() - adv.get(i-1+adv.size()).getHeight();

                if(diff > 0) {
                    posSum += diff;
                    posCount++;
                }
                else if(diff < 0) {
                    negSum += diff;
                    negCount++;
                }
            }

            double absDiff = posSum / posCount - negSum / negCount;
            res.put(hatType, absDiff);
        });
        return res;
    }
}