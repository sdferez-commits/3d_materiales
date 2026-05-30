document.addEventListener('DOMContentLoaded', () => {
  document.querySelectorAll('.flash-msg').forEach(el => {
    setTimeout(() => bootstrap.Alert.getOrCreateInstance(el).close(), 4000);
  });
});
