import { KeycloakService } from 'keycloak-angular';

//Function for initialize keycloak
export function initializeKeycloak(keycloak: KeycloakService):()=> Promise<boolean> {
  return () =>
      keycloak.init({
        config: {
          url: 'http://localhost:8180/auth',
          realm: 'horseraces',
          clientId: 'spa-horseraces',
        },
        initOptions:{
          checkLoginIframe:true,
          checkLoginIframeInterval: 25
        },
        loadUserProfileAtStartUp: true
      });
}
