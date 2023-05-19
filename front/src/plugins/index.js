import VueI18nPlugin from './i18n-extend'
import AuthorityPlugin from './authority-plugin'
import TabsPagePlugin from './tabs-page-plugin'

import VMdEditor from '@kangc/v-md-editor';
import '@kangc/v-md-editor/lib/style/base-editor.css';
import githubTheme from '@kangc/v-md-editor/lib/theme/github.js';
import '@kangc/v-md-editor/lib/theme/style/github.css';


// highlightjs
import hljs from 'highlight.js';

VMdEditor.use(githubTheme, {
  Hljs: hljs,
});

const Plugins = {
  install: function (Vue) {
    Vue.use(VueI18nPlugin)
    Vue.use(AuthorityPlugin)
    Vue.use(TabsPagePlugin)
    Vue.use(VMdEditor)
  }
}
export default Plugins
