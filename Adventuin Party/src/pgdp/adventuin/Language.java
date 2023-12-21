package pgdp.adventuin;

public enum Language {
    ENGLISH {
        @Override
        public String getLocalizedChristmasGreeting(String greeterName) {
            return greeterName + " wishes you a Merry Christmas!";
        }
    }, GERMAN {
        @Override
        public String getLocalizedChristmasGreeting(String greeterName) {
            return "Fröhliche Weihnachten wünscht dir " + greeterName + "!";
        }
    };
    public abstract String getLocalizedChristmasGreeting(String greeterName);

}
