<script>
	let count = 0;

	function test() {
		alert('Hello World')
		count++;
	}
</script>

<style>
	main {
		text-align: center;
	}

	h1 {
		color: #ff3e00;
		text-transform: uppercase;
		font-size: 4em;
		font-weight: 100;
	}
</style>

<main>
	<h1>Hello World</h1>
	<br>
	<button on:click={test}>
		Test Button {count}
	</button>
</main>