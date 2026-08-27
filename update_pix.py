import re

with open('app/src/main/java/com/example/ui/components/DonationDialog.kt', 'r') as f:
    content = f.read()

# Replace DonationPixCard usage
old_usage = """                    // Option: Pix (Brasil)
                    DonationPixCard(
                        pixCode = "00020126580014br.gov.bcb.pix0136ff439919-4119-405d-838a-6c3e3efd8b55520400005303986540526.455802BR5917BRLA DIGITAL LTDA6009Sao Paulo622905258e3dc64ffc0c48fab562857a5630478FA",
                        onCopy = {
                            copyToClipboard(context, "00020126580014br.gov.bcb.pix0136ff439919-4119-405d-838a-6c3e3efd8b55520400005303986540526.455802BR5917BRLA DIGITAL LTDA6009Sao Paulo622905258e3dc64ffc0c48fab562857a5630478FA", "Código Pix Copia e Cola")
                        }
                    )"""

new_usage = """                    // Option 1: Pix (Brasil) 1
                    DonationPixCard(
                        title = "Pix (Brasil) Opción 1",
                        amountText = "Sao Paulo • R$ 5.27",
                        pixCode = "00020126580014br.gov.bcb.pix0136ff439919-4119-405d-838a-6c3e3efd8b5552040000530398654045.275802BR5917BRLA DIGITAL LTDA6009Sao Paulo62290525c898e88196a346fa968d9eada6304654C",
                        onCopy = {
                            copyToClipboard(context, "00020126580014br.gov.bcb.pix0136ff439919-4119-405d-838a-6c3e3efd8b5552040000530398654045.275802BR5917BRLA DIGITAL LTDA6009Sao Paulo62290525c898e88196a346fa968d9eada6304654C", "Código Pix Copia e Cola")
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    // Option 2: Pix (Brasil) 2
                    DonationPixCard(
                        title = "Pix (Brasil) Opción 2",
                        amountText = "Sao Paulo • R$ 26.45",
                        pixCode = "00020126580014br.gov.bcb.pix0136ff439919-4119-405d-838a-6c3e3efd8b55520400005303986540526.455802BR5917BRLA DIGITAL LTDA6009Sao Paulo622905258e3dc64ffc0c48fab562857a5630478FA",
                        onCopy = {
                            copyToClipboard(context, "00020126580014br.gov.bcb.pix0136ff439919-4119-405d-838a-6c3e3efd8b55520400005303986540526.455802BR5917BRLA DIGITAL LTDA6009Sao Paulo622905258e3dc64ffc0c48fab562857a5630478FA", "Código Pix Copia e Cola")
                        }
                    )"""

content = content.replace(old_usage, new_usage)

# Replace DonationPixCard definition
old_def = """private fun DonationPixCard(
    pixCode: String,
    onCopy: () -> Unit
)"""

new_def = """private fun DonationPixCard(
    title: String,
    amountText: String,
    pixCode: String,
    onCopy: () -> Unit
)"""

content = content.replace(old_def, new_def)

# Replace text
content = content.replace('text = "Pix (Brasil)",', 'text = title,')
content = content.replace('text = "Sao Paulo • R$ 26.45",', 'text = amountText,')

with open('app/src/main/java/com/example/ui/components/DonationDialog.kt', 'w') as f:
    f.write(content)
