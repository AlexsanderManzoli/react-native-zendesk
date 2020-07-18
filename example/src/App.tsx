import * as React from 'react';
import { StyleSheet, View, Button } from 'react-native';
import Zendesk from 'react-native-zendesk';
// 204712146778161153
export default function App() {
  async function init() {
    await Zendesk.setup(
      'https://testealex.zendesk.com',
      '5c37c48bffdedd8566c26f2bb66ef5b152eea4323a720b08',
      'mobile_sdk_client_8fee490b4e05ecf7898b'
    );
    await Zendesk.setupChat('61gICS9lKk794W3pYI3AtbuckG3FDKeS');
    await Zendesk.setIdentity(
      'Alexsander Manzoli1',
      'alexsandermanzoli1@gmail.com'
    );
  }

  React.useEffect(() => {
    init();
  }, []);

  return (
    <View style={styles.container}>
      <Button
        title={'Abrir HelpCenter'}
        onPress={() => Zendesk.showHelpCenter(false)}
      />
      <View style={{ padding: 20 }} />

      <Button title={'Meus Tickets'} onPress={() => Zendesk.showRequests('')} />
      <View style={{ padding: 20 }} />
      <Button title={'Abrir Ticket'} onPress={() => Zendesk.showRequest('')} />
      <View style={{ padding: 20 }} />
      <Button title={'Abrir Chat'} onPress={() => Zendesk.showChat()} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
});
