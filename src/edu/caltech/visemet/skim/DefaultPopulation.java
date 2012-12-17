package edu.caltech.visemet.skim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class DefaultPopulation implements Population {

    private final List<Individual> members = new ArrayList<>();

    public DefaultPopulation() { }

    @Override
    public void initialize() {
        for (Individual member : members) {
            member.initialize();
        }
    }

    @Override
    public void expand(final Individual individual) {
        members.add(individual);
    }

    @Override
    public void expand(final List<Individual> individuals) {
        members.addAll(individuals);
    }

    @Override
    public int size() {
        return members.size();
    }

    @Override
    public List<Individual> asList() {
        return Collections.unmodifiableList(members);
    }
}
