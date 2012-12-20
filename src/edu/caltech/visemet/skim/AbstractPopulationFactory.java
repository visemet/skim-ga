package edu.caltech.visemet.skim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @param <I> the type of individuals in this population factory
 *
 * @author Max Hirschhorn #visemet
 */
public abstract class AbstractPopulationFactory<I extends Individual<I>>
implements PopulationFactory<I> {

    /**
     * Class constructor.
     */
    public AbstractPopulationFactory() { }

    @Override
    public Population<I> create() {
        return new DefaultPopulation<>();
    }

    /**
     * Default population implementation.
     *
     * @param <I> the type of individuals in this population
     */
    private static class DefaultPopulation<I extends Individual<I>>
    implements Population<I> {

        /**
         * Holds the list of members of this population.
         */
        private final List<I> members = new ArrayList<>();

        /**
         * Class constructor.
         */
        private DefaultPopulation() { }

        @Override
        public void initialize() {
            for (I member : members) {
                member.initialize();
            }
        }

        @Override
        public void expand(final I individual) {
            members.add(individual);
        }

        @Override
        public void expand(final List<I> individuals) {
            members.addAll(individuals);
        }

        @Override
        public int size() {
            return members.size();
        }

        @Override
        public List<I> asList() {
            return Collections.unmodifiableList(members);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            sb.append("{");

            for (I member : members) {
                sb.append(" ").append(member).append(" ");
            }

            sb.append("}");

            return sb.toString();
        }
    }
}
