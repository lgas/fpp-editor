/*******************************************************************************
 * Copyright (c) 2012-2016 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/
package org.eclipse.che.plugin.fpp.editor.ide.editor;

import com.google.inject.Inject;
import org.eclipse.che.ide.api.app.AppContext;
import org.eclipse.che.ide.rest.AsyncRequestCallback;
import org.eclipse.che.ide.rest.AsyncRequestFactory;
import org.eclipse.che.ide.ui.loaders.request.LoaderFactory;

import java.util.List;

/**
 * Created by gael on 12/08/16.
 */
public class FppCodeAssistClient {
    private final AppContext appContext;
    private final AsyncRequestFactory asyncRequestFactory;
    private final LoaderFactory loaderFactory;

    @Inject
    public FppCodeAssistClient(
            AppContext appContext,
            AsyncRequestFactory asyncRequestFactory,
            LoaderFactory loaderFactory) {
        this.appContext = appContext;
        this.asyncRequestFactory = asyncRequestFactory;
        this.loaderFactory = loaderFactory;

    }

    public void computeProposals(AsyncRequestCallback<List<String>> callback) {
        String url = appContext.getDevMachine().getWsAgentBaseUrl() + "/groovy-completions/";
        asyncRequestFactory
                .createGetRequest(url, false)
                .loader(loaderFactory.newLoader("Loading example completions..."))
                .send(callback);
    }
}
