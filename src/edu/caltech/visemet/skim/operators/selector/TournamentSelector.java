package edu.caltech.visemet.skim.operators.selector;

import edu.caltech.visemet.skim.Base;
import edu.caltech.visemet.skim.Chromosome;
import edu.caltech.visemet.skim.DefaultPopulation;
import edu.caltech.visemet.skim.FitnessEvaluator;
import edu.caltech.visemet.skim.Gene;
import edu.caltech.visemet.skim.Population;
import edu.caltech.visemet.skim.SelectionOperator;
import edu.caltech.visemet.skim.statistics.PopulationStatistics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @param <T> the type of value for bases of genes of chromosomes of this
 * selection operator
 * @param <S> the type of base for genes of chromosomes of this selection
 * operator
 * @param <U> the type of gene for chromosomes of this selection operator
 * @param <V> the type of chromosome for this selection operator
 *
 * @author Max Hirschhorn #visemet
 */
public class TournamentSelector<
        T,
        S extends Base<T>,
        U extends Gene<T, S>,
        V extends Chromosome<T, S, U>
> implements SelectionOperator<T, S, U, V> {

    /**
     * Holds the size of each tournament of this selection operator.
     */
    private int tournamentSize;

    /**
     * Holds the random number generator of this selection operator.
     */
    private Random random = new Random();

    /**
     * Class constructor specifying the size of each tournament
     *
     * @param tournamentSize the size of each tournament
     */
    public TournamentSelector(int tournamentSize) {
        this.tournamentSize = tournamentSize;
    }

    @Override
    public List<V> select(
            int count, FitnessEvaluator<T, S, U, V> evaluator,
            Population<T, S, U, V> population) {

        int populationSize = population.size();
        if (populationSize == 0) {
            throw new IllegalArgumentException(
                    "cannot select from an empty population");
        }

        List<V> selected = new ArrayList<>(count);

        List<V> chromosomes = population.asList();

        while (selected.size() < count) {
            Population<T, S, U, V> subPopulation =
                    new DefaultPopulation<>(new ArrayList<V>());

            for (int num = 0; num < tournamentSize; num++) {
                int index = random.nextInt(populationSize);
                subPopulation.add(chromosomes.get(index));
            }

            selected.add(PopulationStatistics.getMostFitChromosome(
                    evaluator, subPopulation));
        }

        return selected;
    }
}
