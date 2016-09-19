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
package org.eclipse.che.plugin.fpp.editor.ide.inject;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.google.gwt.inject.client.multibindings.GinMultibinder;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import org.eclipse.che.ide.api.extension.ExtensionGinModule;
import org.eclipse.che.ide.api.filetypes.FileType;
import org.eclipse.che.ide.api.project.type.wizard.ProjectWizardRegistrar;
import org.eclipse.che.plugin.fpp.editor.ide.Resources;
import org.eclipse.che.plugin.fpp.editor.ide.editor.FppEditorConfigurationFactory;
import org.eclipse.che.plugin.fpp.editor.ide.project.FppProjectWizardRegistrar;
import org.eclipse.che.plugin.fpp.editor.ide.editor.FppCodeAssistProcessorFactory;

import static org.eclipse.che.plugin.fpp.editor.shared.Constants.MODULE_EXT;
import static org.eclipse.che.plugin.fpp.editor.shared.Constants.SCRIPT_EXT;


/**
 * Created by galaloum on 24/06/2016.
 */
@ExtensionGinModule
public class FppGinModule extends AbstractGinModule {
    @Override
    protected void configure() {
        GinMultibinder.newSetBinder(binder(), ProjectWizardRegistrar.class).addBinding().to(FppProjectWizardRegistrar.class);

        install(new GinFactoryModuleBuilder().build(FppCodeAssistProcessorFactory.class));
        install(new GinFactoryModuleBuilder().build(FppEditorConfigurationFactory.class));
    }

    @Provides
    @Singleton
    @Named("ScriptFileType")
    protected FileType provideScriptFileType() {
        return new FileType(Resources.INSTANCE.icon(), SCRIPT_EXT);
    }

    @Provides
    @Singleton
    @Named("ModuleFileType")
    protected FileType provideModuleFileType() {
        return new FileType(Resources.INSTANCE.icon(), MODULE_EXT);
    }
}
