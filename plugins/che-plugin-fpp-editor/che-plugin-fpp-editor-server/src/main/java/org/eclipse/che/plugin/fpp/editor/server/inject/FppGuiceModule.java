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
package org.eclipse.che.plugin.fpp.editor.server.inject;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import org.eclipse.che.api.project.server.handlers.ProjectHandler;
import org.eclipse.che.api.project.server.type.ProjectTypeDef;
import org.eclipse.che.inject.DynaModule;
import org.eclipse.che.plugin.fpp.editor.server.FppCompletionService;
import org.eclipse.che.plugin.fpp.editor.server.generator.FppCreateProjectHandler;
import org.eclipse.che.plugin.fpp.editor.server.projecttype.FppProjectType;

import static com.google.inject.multibindings.Multibinder.newSetBinder;

/**
 * Created by gael on 04/08/16.
 */
@DynaModule
public class FppGuiceModule extends AbstractModule{
    @Override
    protected void configure() {
        Multibinder<ProjectTypeDef> projectTypeDefMultibinder = newSetBinder(binder(), ProjectTypeDef.class);
        projectTypeDefMultibinder.addBinding().to(FppProjectType.class);

        Multibinder<ProjectHandler> projectHandlerMultibinder = newSetBinder(binder(), ProjectHandler.class);
        projectHandlerMultibinder.addBinding().to(FppCreateProjectHandler.class);

        bind(FppCompletionService.class);
    }
}
