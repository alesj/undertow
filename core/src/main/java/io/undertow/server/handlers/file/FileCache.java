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

package io.undertow.server.handlers.file;

import java.io.File;

import io.undertow.server.HttpCompletionHandler;
import io.undertow.server.HttpServerExchange;

/**
 * A file cache that serves files directly to a client.
 *
 * @author Stuart Douglas
 */
public interface FileCache {

    /**
     * Serves a file directly to the client, once the file has been transferred the completion handler is invoked.
     * <p/>
     * This method essentially takes over the request, once it has been invoked no further handlers should process
     * the request.
     *
     * This method must set the Content-Length header on the {@link HttpServerExchange}.
     *
     * @param exchange          The exchange
     * @param completionHandler The completion handler
     * @param file              The file to serve
     * @throws IllegalStateException If the response channel has already been acquired
     */
    void serveFile(final HttpServerExchange exchange, final HttpCompletionHandler completionHandler, final File file);

}
