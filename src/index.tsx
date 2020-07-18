import { NativeModules } from 'react-native';

type ZendeskType = {
  multiply(a: number, b: number): Promise<number>;
  setup(
    zendeskUrl: string,
    applicationId: string,
    oauthClientId: string
  ): Promise<boolean>;
  setupChat(accountKey: string): Promise<boolean>;
  setIdentity(name: string, email: string): Promise<boolean>;
  showRequests(subject: String): Promise<boolean>;
  showRequest(subject: String): Promise<boolean>;
  showChat(): Promise<boolean>;
  showHelpCenter(contactUsButtonVisible: boolean): Promise<boolean>;
};

const { Zendesk } = NativeModules;

export default Zendesk as ZendeskType;
