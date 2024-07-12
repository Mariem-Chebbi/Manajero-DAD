import { Directive, ElementRef, HostListener } from '@angular/core';
import { AnimationBuilder, animate, style } from '@angular/animations';

@Directive({
  selector: '[appScrollAnimation]'
})
export class ScrollAnimationDirective {

  constructor(private el: ElementRef, private animationBuilder: AnimationBuilder) { }

  @HostListener('window:scroll', [])
  onWindowScroll() {
    const offset = this.el.nativeElement.offsetTop;
    const scrollTop = window.scrollY;
    const distance = Math.abs(scrollTop - offset);
    const duration = 300; // Animation duration in milliseconds

    if (distance < 200) { // Change this value as needed
      const animation = this.animationBuilder.build([
        style({ opacity: 0, transform: 'translateY(-20px)' }),
        animate('0.5s ease', style({ opacity: 1, transform: 'translateY(0)' }))
      ]);
      const player = animation.create(this.el.nativeElement);
      player.play();
    }
  }
}
