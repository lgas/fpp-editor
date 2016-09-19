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

import com.google.gwt.user.client.ui.Widget;
import org.eclipse.che.ide.api.editor.codeassist.CompletionProposal;
import org.eclipse.che.ide.api.icon.Icon;
import org.eclipse.che.plugin.fpp.editor.ide.Resources;

/**
 * Created by galaloum on 24/06/2016.
 */
public class SimpleCompletionProposal implements CompletionProposal{
    private String proposal;

    public SimpleCompletionProposal(String proposal) {
        this.proposal = proposal;
    }

    @Override
    public Widget getAdditionalProposalInfo() {
        return null;
    }

    @Override
    public String getDisplayString() {
        return proposal;
    }

    @Override
    public Icon getIcon() {
        return new Icon("", Resources.INSTANCE.icon());
    }

    @Override
    public void getCompletion(CompletionCallback callback) {
        callback.onCompletion(new SimpleCompletion(proposal));
    }
}
