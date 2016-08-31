package org.unidal.cat.core.view.menu;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.unidal.lookup.annotation.Named;
import org.unidal.web.mvc.ActionContext;

@Named(type = MenuManager.class, instantiationStrategy = Named.PER_LOOKUP)
public class DefaultMenuManager implements MenuManager {
   private Map<String, MenuDef> m_defs = new LinkedHashMap<String, MenuDef>();

   @Override
   public List<Menu> getMenus(ActionContext<?> ctx) {
      List<Menu> menus = new ArrayList<Menu>();

      for (MenuDef def : m_defs.values()) {
         menus.add(new DefaultMenu(def, ctx));
      }

      return menus;
   }

   @Override
   public MenuDef register(String id, String title, String styleClasses, MenuLinkBuilder builder) {
      MenuDef def = m_defs.get(id);

      if (def == null) {
         m_defs.put(id, new MenuDef(id, title, styleClasses, builder));
      }

      return def;
   }
}
