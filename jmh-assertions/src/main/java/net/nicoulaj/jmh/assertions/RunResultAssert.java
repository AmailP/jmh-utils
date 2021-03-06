/*
 * ====================================================================
 * JMH utils :: assertions
 * ====================================================================
 * Copyright (C) 2014 Julien Nicoulaud <julien.nicoulaud@gmail.com>
 * ====================================================================
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * ====================================================================
 */
package net.nicoulaj.jmh.assertions;


import org.openjdk.jmh.results.RunResult;

/**
 * Default implementation of {@link net.nicoulaj.jmh.assertions.JMHRunResultAssert}.
 *
 * @author <a href="http://github.com/nicoulaj">nicoulaj</a>
 * @see net.nicoulaj.jmh.assertions.JMHAssertions
 */
final class RunResultAssert implements JMHRunResultAssert {

    private final RunResult runResult;

    RunResultAssert(final RunResult runResult) {
        this.runResult = runResult;
    }

    @Override
    public JMHRunResultAssert hasSingleResult() {
        return this;
    }

    @Override
    public JMHRunResultAssert hasResults() {
        return this;
    }

    public JMHRunResultAssert hasScoreOver(double value) {
        return hasPrimaryScoreOver(value);
    }

    public JMHRunResultAssert hasScoreOver(double value, double tolerance) {
        return hasPrimaryScoreOver(value, tolerance);
    }

    public JMHRunResultAssert hasPrimaryScoreOver(double value) {
        return hasPrimaryScoreOver(value, 0);
    }

    public JMHRunResultAssert hasPrimaryScoreOver(double value, double tolerance) {
        final double primaryScore = runResult.getPrimaryResult().getScore();
        if (primaryScore < value - tolerance)
            throw new AssertionError("Expected score over " + format(value, tolerance) + ", actual score is " + primaryScore);
        return this;
    }

    private static String format(double value, double tolerance) {
        if (tolerance == 0) return String.valueOf(value);
        return value + "±" + tolerance;
    }
}
