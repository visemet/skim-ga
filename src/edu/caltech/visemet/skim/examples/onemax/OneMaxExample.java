package edu.caltech.visemet.skim.examples.onemax;

import edu.caltech.visemet.skim.*;
import edu.caltech.visemet.skim.algorithms.ThresholdTerminationGeneticAlgorithm;
import edu.caltech.visemet.skim.genes.BooleanBase;
import edu.caltech.visemet.skim.genes.BooleanGene;
import edu.caltech.visemet.skim.operators.crossover.NpointCrossover;
import edu.caltech.visemet.skim.operators.mutator.InversionMutator;
import edu.caltech.visemet.skim.operators.selector.RouletteWheelSelector;
import edu.caltech.visemet.skim.statistics.PopulationStatistics;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

/**
 *
 * @param <T> the type of value for bases of genes of chromosomes of this
 * example
 * @param <S> the type of base for genes of chromosomes of this example
 * @param <U> the type of gene for chromosomes of this example
 * @param <V> the type of chromosome for this example
 *
 * @author Max Hirschhorn #visemet
 */
public class OneMaxExample<
        T extends Boolean,
        S extends Base<T>,
        U extends Gene<T, S>,
        V extends Chromosome<T, S, U>
> extends AbstractExample<T, S, U, V> {

    /**
     * Class constructor specifying the example configuration.
     *
     * @param config the configuration used by this example
     */
    public OneMaxExample(ExampleConfiguration config) {
        super(config);
    }

    @Override
    protected void createEvaluator() {
        setEvaluator(new OneMaxFitnessFunction<T, S, U, V>());
    }

    @Override
    protected void createSelector() {
        setSelector(new RouletteWheelSelector<T, S, U, V>());
    }

    @Override
    protected void createPopulation() {
        setPopulation(new DefaultPopulation<>(new ArrayList<V>()));

        Population<T, S, U, V> population = getPopulation();
        ExampleConfiguration config = getConfig();

        int geneLength = config.getGeneLength();
        int populationSize = config.getPopulationSize();

        for (int count = 0; count < populationSize; count++) {
            List<U> genes = new ArrayList<>();

            genes.add((U) new BooleanGene(geneLength));
            population.add((V) new DefaultChromosome<>(genes));
        }

        population.initialize();
    }

    @Override
    protected void createAlgorithm() {
        ExampleConfiguration config = getConfig();

        int geneLength = config.getGeneLength();
        double crossoverProbability = config.getCrossoverProbability();
        double mutationProbability = config.getMutationProbability();

        setAlgorithm(new ThresholdTerminationGeneticAlgorithm<T, S, U, V>(
                geneLength,
                new GeneticAlgorithmConfiguration.Builder<T, S, U>(1)
                        .setShouldRetainMostFit(true)
                        .setNumCrossoverParents(2)
                        .setCrossoverProbability(0, crossoverProbability)
                        .setMutationProbability(0, mutationProbability)
                        .setCrossover(0, new NpointCrossover<T, S, U>(1))
                        .setMutator(0, new InversionMutator<T, S, U>())
                        .build()));
    }

    @Override
    public void execute() {
        GeneticAlgorithm<T, S, U, V> algorithm = getAlgorithm();

        int numGenerations = 10;

        int count = 0;
        while (!algorithm.shouldTerminate()) {
            execute(numGenerations);
            count += numGenerations;
        }

        FitnessEvaluator<T, S, U, V> evaluator = getEvaluator();
        Population<T, S, U, V> population = getPopulation();

        V chromosome = PopulationStatistics.getMostFitChromosome(
                evaluator, population);

        double fitness = evaluator.evaluate(chromosome);

        System.out.printf(
                "Found solution %s with fitness %.6f after %d generations.\n",
                chromosome, fitness, count);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CommandLineParser parser = new PosixParser();

        Options options = new Options();

        Option help = new Option("h", "help", false, "print this message");

        @SuppressWarnings({"static", "static-access"})
        Option gene = OptionBuilder
                .withLongOpt("gene-length")
                .withDescription("length of gene")
                .hasArg()
                .withArgName("LENGTH")
                .withType(Number.class)
                .isRequired()
                .create("gl");

        @SuppressWarnings({"static", "static-access"})
        Option population = OptionBuilder
                .withLongOpt("population-size")
                .withDescription("size of population")
                .hasArg()
                .withArgName("SIZE")
                .withType(Number.class)
                .isRequired()
                .create("ps");

        @SuppressWarnings({"static", "static-access"})
        Option crossover = OptionBuilder
                .withLongOpt("crossover-prob")
                .withDescription("probability of crossover")
                .hasArg()
                .withArgName("PROB")
                .withType(Number.class)
                .isRequired()
                .create("cp");

        @SuppressWarnings({"static", "static-access"})
        Option mutation = OptionBuilder
                .withLongOpt("mutation-prob")
                .withDescription("probability of mutation")
                .hasArg()
                .withArgName("PROB")
                .withType(Number.class)
                .isRequired()
                .create("mp");

        options.addOption(help);
        options.addOption(gene);
        options.addOption(population);
        options.addOption(crossover);
        options.addOption(mutation);

        try {
            // Parses the command line arguments
            CommandLine line = parser.parse(options, args);

            if (line.hasOption(help.getOpt())) {
                throw new ParseException(null);
            }

            int geneLength = (int) (long) line.getParsedOptionValue(
                    gene.getOpt());

            int populationSize = (int) (long) line.getParsedOptionValue(
                    population.getOpt());

            double crossoverProbability = (double) line.getParsedOptionValue(
                    crossover.getOpt());

            double mutationProbability = (double) line.getParsedOptionValue(
                    mutation.getOpt());

            ExampleConfiguration config = new ExampleConfiguration.Builder()
                    .setGeneLength(geneLength)
                    .setPopulationSize(populationSize)
                    .setCrossoverProbability(crossoverProbability)
                    .setMutationProbability(mutationProbability)
                    .build();

            Example<
                    Boolean,
                    BooleanBase,
                    BooleanGene<Boolean, BooleanBase>,
                    DefaultChromosome<
                            Boolean,
                            BooleanBase,
                            BooleanGene<Boolean, BooleanBase>
                    >
            > example = new OneMaxExample<>(config);

            example.configure();
            example.execute();
        } catch (ParseException ex) {
            String message = ex.getMessage();
            if (message != null) {
                System.err.println(message);
            }

            PrintWriter pw = new PrintWriter(System.out);
            int width = 72;
            String cmdLineSyntax = "sh onemax.sh";
            String header = "options:";
            String footer =
                    "example: sh onemax.sh -gl 60 -ps 30 -cp 0.8 -mp 0.02";
            int leftPad = 2;
            int descPad = 4;
            boolean autoUsage = true;

            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp(
                    pw, width, cmdLineSyntax, header, options, leftPad, descPad,
                    footer, autoUsage);

            pw.flush();
        }
    }
}
