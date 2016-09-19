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
package org.eclipse.che.plugin.fpp.editor.ide;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.eclipse.che.ide.api.action.ActionManager;
import org.eclipse.che.ide.api.action.DefaultActionGroup;
import org.eclipse.che.ide.api.constraints.Constraints;
import org.eclipse.che.ide.api.editor.EditorRegistry;
import org.eclipse.che.ide.api.extension.Extension;
import org.eclipse.che.ide.api.filetypes.FileType;
import org.eclipse.che.ide.editor.orion.client.OrionContentTypeRegistrant;
import org.eclipse.che.ide.editor.orion.client.jso.OrionContentTypeOverlay;
import org.eclipse.che.ide.editor.orion.client.jso.OrionHighlightingConfigurationOverlay;
import org.eclipse.che.plugin.fpp.editor.ide.action.CreateModuleFileAction;
import org.eclipse.che.plugin.fpp.editor.ide.action.CreateScriptFileAction;
import org.eclipse.che.plugin.fpp.editor.ide.editor.FppEditorProvider;

import static org.eclipse.che.ide.api.action.IdeActions.GROUP_FILE_NEW;
import static org.eclipse.che.plugin.fpp.editor.shared.Constants.MODULE_EXT;
import static org.eclipse.che.plugin.fpp.editor.shared.Constants.SCRIPT_EXT;

/**
 * Created by galaloum on 24/06/2016.
 */
@Extension(title = "FPP Editor")
public class FppJSEditorExtension {

    @Inject
    public FppJSEditorExtension(final EditorRegistry eRegistry,
                                @Named("ScriptFileType") final FileType sFType,
                                @Named("ModuleFileType") final FileType mFType,
                                final FppEditorProvider fppEditorProvider,
                                CreateScriptFileAction createScriptFileAction,
                                CreateModuleFileAction createModuleFileAction,
                                ActionManager actionManager) {

        eRegistry.registerDefaultEditor(sFType, fppEditorProvider);
        eRegistry.registerDefaultEditor(mFType, fppEditorProvider);

        DefaultActionGroup newGroup = (DefaultActionGroup)actionManager.getAction(GROUP_FILE_NEW);
        actionManager.registerAction("newScriptFile", createScriptFileAction);
        actionManager.registerAction("newModuleFile", createModuleFileAction);
        newGroup.add(createScriptFileAction, Constraints.FIRST);
        newGroup.add(createModuleFileAction, Constraints.FIRST);
    }

    @Inject
    protected void configureContentType(final OrionContentTypeRegistrant contentTypeRegistrant) {
        // register content type and configure orion
        final String contentTypeId = "application/fpp";

        OrionContentTypeOverlay contentType = OrionContentTypeOverlay.create();
        contentType.setId(contentTypeId);
        contentType.setName("FPP Language");
        contentType.setExtension(SCRIPT_EXT, MODULE_EXT);
//        contentType.setExtends("text/plain");

        // highlighting
        OrionHighlightingConfigurationOverlay config = OrionHighlightingConfigurationOverlay.create();
        config.setId("orion.fpp");
        config.setContentTypes(contentTypeId);
        config.setPatterns(
                "[\n" +
                        "  {include: \"orion.java\"},\n" +
                        "  {\n" +
                        "    match: \"\\\\b(?:it|delegate|owner|as|in|def|threadsafe|assert|use|with)\\\\b\",\n" +
                        "    name: \"keyword.fpp\"\n" +
                        "  }\n" +
                        "]");

        contentTypeRegistrant.registerFileType(contentType, config);
    }
}
