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
import org.eclipse.che.ide.api.editor.codeassist.CodeAssistCallback;
import org.eclipse.che.ide.api.editor.codeassist.CodeAssistProcessor;
import org.eclipse.che.ide.api.editor.codeassist.CompletionProposal;
import org.eclipse.che.ide.api.editor.texteditor.TextEditor;
import org.eclipse.che.ide.rest.AsyncRequestCallback;
import org.eclipse.che.ide.rest.Unmarshallable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by galaloum on 24/06/2016.
 */
public class FppCodeAssistProcessor implements CodeAssistProcessor {

    private final FppCodeAssistClient client;
    private final Unmarshallable<List<String>> unmarshaller;
    private String errorMessage;

    @Inject
    public FppCodeAssistProcessor(final FppCodeAssistClient client) {
        this.client = client;
        this.unmarshaller = new StringListUnmarshaller();
        this.errorMessage = null;
    }

    @Override
    public void computeCompletionProposals(final TextEditor editor, final int offset, final CodeAssistCallback callback) {
        final List<CompletionProposal> proposals = new ArrayList<>();

        client.computeProposals(new AsyncRequestCallback<List<String>>(unmarshaller) {
            @Override
            protected void onSuccess(List<String> additionalProposals) {
                errorMessage = null;

                for (String additionalProposal : additionalProposals) {
                    proposals.add(new SimpleCompletionProposal(additionalProposal));
                }
                callback.proposalComputed(proposals);
            }

            @Override
            protected void onFailure(Throwable exception) {
                errorMessage = exception.getMessage();
            }
        });

        callback.proposalComputed(proposals);
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
