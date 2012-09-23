package edu.caltech.visemet.skim.genes;

import edu.caltech.visemet.skim.Base;
import java.util.Objects;
import java.util.Random;

/**
 * A base implementation that holds a {@code char} value.
 *
 * @author Max Hirschhorn #visemet
 */
public class CharacterBase implements Base<Character> {

    /**
     * Defines the character set of lowercase letters for the bases.
     */
    public static final char[] LOWERCASE_LETTERS =
            "abcdefghijklmnopqrstuvwxyz".toCharArray();

    /**
     * Defines the character set of uppercase letters for the bases.
     */
    public static final char[] UPPERCASE_LETTERS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    /**
     * Defines the character set of digits for the bases.
     */
    public static final char[] DIGITS = "0123456789".toCharArray();

    /**
     * Holds the character set of this base.
     */
    private char[] set;

    /**
     * Holds the value of this base.
     */
    private char value;

    /**
     * Class constructor specifying the character set and the value.
     *
     * @param set the character set of this base
     * @param value the value of this base
     */
    public CharacterBase(char[] set, char value) {
        this.set = set;
        this.value = value;
    }

    @Override
    public void randomize(Random random) {
        setValue(set[random.nextInt(set.length)]);
    }

    @Override
    public Character getValue() {
        return value;
    }

    @Override
    public void setValue(Character value) {
        this.value = value;
    }

    @Override
    public CharacterBase copy() {
        return new CharacterBase(set, value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final CharacterBase other = (CharacterBase) obj;

        return Objects.equals(this.value, other.value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
