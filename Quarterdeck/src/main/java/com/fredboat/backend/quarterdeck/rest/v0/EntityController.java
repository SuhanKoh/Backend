/*
 * MIT License
 *
 * Copyright (c) 2016-2018 The FredBoat Org https://github.com/FredBoat/
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.fredboat.backend.quarterdeck.rest.v0;

import com.fredboat.backend.quarterdeck.db.repositories.api.Repo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import space.npstr.sqlsauce.entities.SaucedEntity;

import java.io.Serializable;

/**
 * Created by napster on 17.02.18.
 * <p>
 * Oh no, all those PostMappings are totally against Https/Rest principles...too bad that GET / DELETE do not support
 * RequestBodies, and our Ids can be a bit more than a simple string / long. So they are passed as json as part of the
 * body.
 */
public abstract class EntityController<I extends Serializable, E extends SaucedEntity<I, E>> {

    public static final int API_VERSION = 0;
    public static final String VERSION_PATH = "v" + API_VERSION + "/";

    protected final Repo<I, E> repo;

    public EntityController(Repo<I, E> repo) {
        this.repo = repo;
    }

    @PostMapping("/delete")
    public void delete(@RequestBody I id) {
        this.repo.delete(id);
    }

    @PostMapping("/fetch")
    public E fetch(@RequestBody I id) {
        return this.repo.fetch(id);
    }

    @PostMapping("/merge")
    public E merge(@RequestBody E entity) {
        return this.repo.merge(entity);
    }
}
