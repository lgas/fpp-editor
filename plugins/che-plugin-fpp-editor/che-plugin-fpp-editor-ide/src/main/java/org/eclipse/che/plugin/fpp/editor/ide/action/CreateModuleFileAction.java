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
package org.eclipse.che.plugin.fpp.editor.ide.action;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import org.eclipse.che.ide.CoreLocalizationConstant;
import org.eclipse.che.ide.api.app.AppContext;
import org.eclipse.che.ide.api.dialogs.DialogFactory;
import org.eclipse.che.ide.api.notification.NotificationManager;
import org.eclipse.che.ide.newresource.AbstractNewResourceAction;
import org.eclipse.che.plugin.fpp.editor.ide.Resources;

import static org.eclipse.che.plugin.fpp.editor.shared.Constants.MODULE_EXT;

/**
 * Created by gael on 18/08/16.
 */
public class CreateModuleFileAction extends AbstractNewResourceAction {

    @Inject
    public CreateModuleFileAction(Resources fppResources, DialogFactory dialogFactory, CoreLocalizationConstant coreLocalizationConstant, EventBus eventBus, AppContext appContext, NotificationManager notificationManager) {

        super("FPP Module File", "Create new FPP Module file.", fppResources.icon(), dialogFactory, coreLocalizationConstant, eventBus, appContext, notificationManager);
    }

    @Override
    protected String getExtension() {
        return MODULE_EXT;
    }

    @Override
    protected String getDefaultContent() {
        return "";
    }
}
