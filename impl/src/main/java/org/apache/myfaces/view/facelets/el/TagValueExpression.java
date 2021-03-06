/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.myfaces.view.facelets.el;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import jakarta.el.ELContext;
import jakarta.el.ELException;
import jakarta.el.PropertyNotFoundException;
import jakarta.el.PropertyNotWritableException;
import jakarta.el.ValueExpression;
import jakarta.el.ValueReference;
import jakarta.faces.FacesWrapper;
import jakarta.faces.view.facelets.TagAttribute;

/**
 * 
 * 
 * @author Jacob Hookom
 * @version $Id$
 */
public class TagValueExpression extends ValueExpression implements Externalizable, FacesWrapper<ValueExpression>
{

    private static final long serialVersionUID = 1L;

    // orig and attr need to be available in TagValueExpressionUEL
    ValueExpression orig; 
    String attr; 

    public TagValueExpression()
    {
        super();
    }

    public TagValueExpression(TagAttribute attr, ValueExpression orig)
    {
        this.attr = attr.toString();
        this.orig = orig;
    }

    @Override
    public Class<?> getExpectedType()
    {
        return this.orig.getExpectedType();
    }

    @Override
    public Class<?> getType(ELContext context)
    {
        try
        {
            return this.orig.getType(context);
        }
        catch (PropertyNotFoundException pnfe)
        {
            throw new PropertyNotFoundException(this.attr + ": " + pnfe.getMessage(), pnfe.getCause());
        }
        catch (ELException e)
        {
            throw new ELException(this.attr + ": " + e.getMessage(), e.getCause());
        }
    }

    @Override
    public Object getValue(ELContext context)
    {
        try
        {
            return this.orig.getValue(context);
        }
        catch (PropertyNotFoundException pnfe)
        {
            throw new PropertyNotFoundException(this.attr + ": " + pnfe.getMessage(), pnfe.getCause());
        }
        catch (ELException e)
        {
            throw new ELException(this.attr + ": " + e.getMessage(), e.getCause());
        }
    }

    @Override
    public boolean isReadOnly(ELContext context)
    {
        try
        {
            return this.orig.isReadOnly(context);
        }
        catch (PropertyNotFoundException pnfe)
        {
            throw new PropertyNotFoundException(this.attr + ": " + pnfe.getMessage(), pnfe.getCause());
        }
        catch (ELException e)
        {
            throw new ELException(this.attr + ": " + e.getMessage(), e.getCause());
        }
    }

    @Override
    public void setValue(ELContext context, Object value)
    {
        try
        {
            this.orig.setValue(context, value);
        }
        catch (PropertyNotFoundException pnfe)
        {
            throw new PropertyNotFoundException(this.attr + ": " + pnfe.getMessage(), pnfe.getCause());
        }
        catch (PropertyNotWritableException pnwe)
        {
            throw new PropertyNotWritableException(this.attr + ": " + pnwe.getMessage(), pnwe.getCause());
        }
        catch (ELException e)
        {
            throw new ELException(this.attr + ": " + e.getMessage(), e.getCause());
        }
    }
    
    @Override
    public boolean equals(Object obj)
    {
        return this.orig.equals(obj);
    }

    @Override
    public String getExpressionString()
    {
        return this.orig.getExpressionString();
    }

    @Override
    public int hashCode()
    {
        return this.orig.hashCode();
    }

    @Override
    public boolean isLiteralText()
    {
        return this.orig.isLiteralText();
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
    {
        this.orig = (ValueExpression) in.readObject();
        this.attr = in.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException
    {
        out.writeObject(this.orig);
        out.writeUTF(this.attr);
    }

    @Override
    public String toString()
    {
        return this.attr + ": " + this.orig;
    }

    @Override
    public ValueExpression getWrapped()
    {
        return orig;
    }
    
    @Override
    public ValueReference getValueReference(ELContext context)
    {
        try
        {
            return this.orig.getValueReference(context);
        }
        catch (PropertyNotFoundException pnfe)
        {
            throw new PropertyNotFoundException(this.attr + ": " + pnfe.getMessage(), pnfe.getCause());
        }
        catch (ELException e)
        {
            throw new ELException(this.attr + ": " + e.getMessage(), e.getCause());
        }
    }
}
