/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2012 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.undertow.server.httpparser;

import java.util.List;

/**
 * The current state of the tokenizer state machine. This class is mutable and not thread safe.
 * <p/>
 * As the machine changes state this class is updated rather than allocating a new one each time.
 *
 * fields are not private to allow for efficient putfield / getfield access
 *
 * @author Stuart Douglas
 */
public class ParseState {

    //parsing states
    public static final int VERB = 0;
    public static final int PATH = 1;
    public static final int VERSION = 2;
    public static final int HEADER = 3;
    public static final int HEADER_VALUE = 4;
    public static final int PARSE_COMPLETE = 5;

    /**
     * The actual state of request parsing
     */
    int state;

    /**
     * The current state in the tokenizer state machine.
     */
    int parseState;

    /**
     * If this state is a prefix or terminal match state this is set to the string
     * that is a candiate to be matched
     */
    String current;

    /**
     * The bytes version of {@link #current}
     */
    byte[] currentBytes;

    /**
     * If this state is a prefix match state then this holds the current position in the string.
     */
    int pos;

    int queryParamPos;

    /**
     * If this is in {@link #NO_STATE} then this holds the current token that has been read so far.
     */
    StringBuilder stringBuilder;

    /**
     * This has different meanings depending on the current state.
     *
     * In state {@link #HEADER} it is a the first character of the header, that was read by
     * {@link #HEADER_VALUE} to see if this was a continuation.
     *
     * In state {@link #HEADER_VALUE} if represents the last character that was seen.
     */
    byte leftOver;


    /**
     * This is used to store the next header value when parsing header key / value pairs,
     */
    String nextHeader;


    public ParseState() {
        this.parseState = 0;
        this.current = null;
        this.pos = 0;
    }

    public boolean isComplete() {
        return state == PARSE_COMPLETE;
    }
}
