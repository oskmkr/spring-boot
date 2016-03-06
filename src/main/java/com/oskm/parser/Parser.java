package com.oskm.parser;

import java.util.List;

/**
 * Created by oskm on 2015-12-06.
 */
public interface Parser<T> {
    List<T> parse();
}
