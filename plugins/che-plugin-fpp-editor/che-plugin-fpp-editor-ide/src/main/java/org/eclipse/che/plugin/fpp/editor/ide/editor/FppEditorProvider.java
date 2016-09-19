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
import org.eclipse.che.ide.api.editor.defaulteditor.AbstractTextEditorProvider;
import org.eclipse.che.ide.api.editor.editorconfig.TextEditorConfiguration;
import org.eclipse.che.ide.api.editor.texteditor.TextEditor;

/**
 * Created by galaloum on 24/06/2016.
 */
public class FppEditorProvider extends AbstractTextEditorProvider {

    private final FppEditorConfigurationFactory editorConfigurationFactory;

    @Inject
    public FppEditorProvider(final FppEditorConfigurationFactory editorConfigurationFactory) {
        this.editorConfigurationFactory = editorConfigurationFactory;
    }

    @Override
    public String getId() {
        return "FPP_EDITOR";
    }

    @Override
    public String getDescription() {
        return "FPP Editor";
    }

    @Override
    public TextEditor getEditor() {
        TextEditor editor = super.getEditor();
        TextEditorConfiguration configuration = this.editorConfigurationFactory.create(editor);
        editor.initialize(configuration);
        return editor;
    }
}
