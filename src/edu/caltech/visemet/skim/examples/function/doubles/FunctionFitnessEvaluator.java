package edu.caltech.visemet.skim.examples.function.doubles;

import edu.caltech.visemet.skim.Chromosome;
import edu.caltech.visemet.skim.FitnessEvaluator;
import edu.caltech.visemet.skim.Gene;
import edu.caltech.visemet.skim.NumberBase;

/**
 *
 * @param <T> the type of value for bases of genes of chromosomes of this
 * fitness evaluator
 * @param <S> the type of base for genes of chromosomes of this fitness
 * evaluator
 * @param <U> the type of gene for chromosomes of this fitness evaluator
 * @param <V> the type of chromosome for this fitness evaluator
 *
 * @author Max Hirschhorn #visemet
 */
public class FunctionFitnessEvaluator<
        T extends Double,
        S extends NumberBase<T>,
        U extends Gene<T, S>,
        V extends Chromosome<T, S, U>
> implements FitnessEvaluator<T, S, U, V> {

    /**
     * Holds the function used by this fitness evaluator.
     */
    private final Function function;

    /**
     * Class constructor specifying the function used by this fitness evaluator.
     *
     * @param function the function used by this fitness evaluator
     */
    public FunctionFitnessEvaluator(Function function) {
        this.function = function;
    }

    /**
     * Checks whether the specified chromosome is {@code null}.
     *
     * @param chromosome the chromosome to be checked
     *
     * @throws IllegalArgumentException if the specified chromosome is
     * {@code null}
     */
    private void validateChromosome(V chromosome) {
        if (chromosome == null) {
            throw new IllegalArgumentException("chromosome cannot be null");
        }
    }

    /**
     * Checks whether the specified gene is {@code null} or does not contain a
     * single base.
     *
     * @param gene the gene to be checked
     *
     * @throws IllegalArgumentException if the specified gene is {@code null} or
     * does not contain a single base
     */
    private void validateGene(U gene) {
        if (gene == null) {
            throw new IllegalArgumentException("gene cannot be null");
        } else if (gene.length() != 1) {
            throw new IllegalArgumentException(
                    "gene must contain a single base");
        }
    }

    /**
     * Checks whether the specified base is {@code null}.
     *
     * @param base the base to be checked
     *
     * @throws IllegalArgumentException if the specified base is {@code null}
     */
    private void validateBase(S base) {
        if (base == null) {
            throw new IllegalArgumentException("base cannot be null");
        }
    }

    @Override
    public double evaluate(V chromosome) {
        validateChromosome(chromosome);

        int chromosomeLength = chromosome.length();
        double[] vars = new double[chromosomeLength];

        for (int geneIndex = 0; geneIndex < chromosomeLength; geneIndex++) {
            U gene = chromosome.getGeneAt(geneIndex);
            validateGene(gene);

            S base = gene.getBaseAt(0);
            validateBase(base);

            double value = base.getValue();
            vars[geneIndex] = value;
        }

        return function.evaluate(vars);
    }
}
