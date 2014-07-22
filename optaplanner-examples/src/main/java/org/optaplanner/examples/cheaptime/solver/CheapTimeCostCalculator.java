/*
 * Copyright 2014 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.optaplanner.examples.cheaptime.solver;

import java.math.BigDecimal;

public class CheapTimeCostCalculator {

    public static final long ONE_MILLION_LONG = 100000000000L;
    public static final double ONE_MILLION_DOUBLE = 100000000000.0;
    public static final BigDecimal ONE_MILLION_BIG_DECIMAL = new BigDecimal(ONE_MILLION_LONG);

    public static long toMicroCost(long cost) {
        return cost * ONE_MILLION_LONG;
    }

    public static long parseMicroCost(String costString) {
        BigDecimal costBigDecimal = new BigDecimal(costString);
        if (costBigDecimal.scale() > 11) {
            throw new IllegalArgumentException("The costString (" + costString + ") has a scale ("
                    + costBigDecimal.scale() + ") higher than 10.");
        }
        costBigDecimal = costBigDecimal.setScale(11);
        return costBigDecimal.multiply(ONE_MILLION_BIG_DECIMAL).longValueExact();
    }

    public static long multiplyTwoMicros(long aMicros, long bMicros) {
        double aDouble = ((double) (aMicros)) / ONE_MILLION_DOUBLE;
        double bDouble = ((double) (bMicros)) / ONE_MILLION_DOUBLE;
        double result = aDouble * bDouble;
        return Math.round(result * ONE_MILLION_DOUBLE);
    }

    public static long divideTwoMicros(long aMicros, long bMicros) {
        double aDouble = ((double) (aMicros)) / ONE_MILLION_DOUBLE;
        double bDouble = ((double) (bMicros)) / ONE_MILLION_DOUBLE;
        double result = aDouble / bDouble;
        return Math.round(result * ONE_MILLION_DOUBLE);
    }

    private CheapTimeCostCalculator() {}

}
