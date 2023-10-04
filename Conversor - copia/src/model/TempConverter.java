package model;

public class TempConverter {
    private static float celsiusToFahrenheit(float celsius) {
        return (celsius * 9f / 5f) + 32f;
    }

    private static float fahrenheitToCelsius(float fahrenheit) {
        return (fahrenheit - 32f) * 5f / 9f;
    }

    private static float fahrenheitToKelvin(float fahrenheit) {
        return (fahrenheit - 32) * 5f / 9f + 273.15f;
    }

    private static float kelvinToFahrenheit(float kelvin) {
        return (kelvin - 273.15f) * 9f / 5f + 32;
    }

    public static float convert(float value, TemperatureDB fromUnit, TemperatureDB toUnit) {
        if (fromUnit == TemperatureDB.CELSIUS) {
            if (toUnit == TemperatureDB.FAHRENHEIT) {
                return celsiusToFahrenheit(value);
            } else if (toUnit == TemperatureDB.KELVIN) {
                return fahrenheitToKelvin(celsiusToFahrenheit(value));
            } else {
                return value;
            }
        } else if (fromUnit == TemperatureDB.FAHRENHEIT) {
            if (toUnit == TemperatureDB.CELSIUS) {
                return fahrenheitToCelsius(value);
            } else if (toUnit == TemperatureDB.KELVIN) {
                return fahrenheitToKelvin(value);
            } else {
                return value;
            }
        } else {
            if (toUnit == TemperatureDB.CELSIUS) {
                return fahrenheitToCelsius(kelvinToFahrenheit(value));
            } else if (toUnit == TemperatureDB.FAHRENHEIT) {
                return kelvinToFahrenheit(value);
            } else {
                return value;
            }
        }
    }

    public static TempResolver from(float value, TemperatureDB unit) {
        return new TempResolver(value, unit);
    }

    public static class TempResolver {
        private final float value;
        private final TemperatureDB fromUnit;

        private TempResolver(float value, TemperatureDB fromUnit) {
            this.value = value;
            this.fromUnit = fromUnit;
        }

        public float to(TemperatureDB toUnit) {
            return convert(value, fromUnit, toUnit);
        }
    }
}
