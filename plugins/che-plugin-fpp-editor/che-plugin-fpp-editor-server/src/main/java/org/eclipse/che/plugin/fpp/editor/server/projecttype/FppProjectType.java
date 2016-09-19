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
package org.eclipse.che.plugin.fpp.editor.server.projecttype;

import com.google.inject.Inject;
import org.eclipse.che.api.project.server.type.ProjectTypeDef;

import static org.eclipse.che.plugin.fpp.editor.shared.Constants.*;

/**
 * Created by gael on 04/08/16.
 */
public class FppProjectType extends ProjectTypeDef {
    @Inject
    public FppProjectType() {
        super(FPP_PROJECT_TYPE_ID, FPP_CATEGORY, true, false, true);

    }
}
