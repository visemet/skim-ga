package edu.caltech.visemet.skim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Max Hirschhorn #visemet
 */
public class DefaultPopulation<I extends Individual<I>> implements Population<I> {

    private final List<I> members = new ArrayList<>();

    public DefaultPopulation() { }

    @Override
    public void initialize() {
        for (Individual member : members) {
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
}
